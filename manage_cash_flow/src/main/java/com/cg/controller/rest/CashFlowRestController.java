package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.model.CashFlow;
import com.cg.model.dto.CashFlowDTO;
import com.cg.model.dto.CashFlowStringDTO;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.MethodDTO;
import com.cg.service.cashFlow.ICashFlowService;
import com.cg.service.category.ICategoryService;
import com.cg.service.method.IMethodService;
import com.cg.util.AppUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cashFlows")
public class CashFlowRestController {
    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ICashFlowService cashFlowService;

    @Autowired
    private IMethodService methodService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAllByDeletedIsFalse(){
        List<CashFlowDTO> cashFlows = cashFlowService.findAllByDeletedIsFalse();
        if(cashFlows.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cashFlows, HttpStatus.OK);
    }

    @GetMapping("/{cashFlowId}")
    public ResponseEntity<?> getById(@PathVariable Long cashFlowId){
        Optional<CashFlow> cashFlowOptional = cashFlowService.findById(cashFlowId);
        if(!cashFlowOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CashFlow cashFlow = cashFlowOptional.get();
        return new ResponseEntity<>(cashFlow.toCashFlowDTO(), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/{dateStart}/{dateEnd}")
    public ResponseEntity<?>getAllCashFlowBetweenDate(@PathVariable String dateStart, @PathVariable String dateEnd){
        Date dateStartNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStart);
        Date dateEndNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateEnd);

       List<CashFlowDTO> cashFlows = cashFlowService.getAllCashFlowBetweenDate(dateStartNew,dateEndNew);

       if(cashFlows.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(cashFlows, HttpStatus.OK);
    }

    @GetMapping("/methods")
    public ResponseEntity<?>getAllMethos(){
        List<MethodDTO> methods = methodService.getAllMethodDTO();
        return new ResponseEntity<>(methods,HttpStatus.OK);
    }

    @GetMapping("/categorys")
    public ResponseEntity<?>getAllCategorys(){
        List<CategoryDTO> categorys = categoryService.getAllCategorysDTO();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("/cashFlowThus")
    public ResponseEntity<?> getAllCashFlowThus(){
        List<CashFlowDTO> cashFlows = cashFlowService.choiceMethod(1L);
        return new ResponseEntity<>(cashFlows, HttpStatus.OK);
    }
    @GetMapping("/cashFlowChis")
    public ResponseEntity<?> getAllCashFlowChis(){
        List<CashFlowDTO> cashFlows = cashFlowService.choiceMethod(2L);
        return new ResponseEntity<>(cashFlows, HttpStatus.OK);
    }

    @GetMapping("/cashFlowNos")
    public ResponseEntity<?> getAllCashFlowNos(){
        List<CashFlowDTO> cashFlows = cashFlowService.choiceMethod(3L);
        return new ResponseEntity<>(cashFlows, HttpStatus.OK);
    }

    @GetMapping("/getMoneyThu")
    public ResponseEntity<?> getMoneyThu(){
        BigDecimal getMoneyThu = cashFlowService.getSumChoiceMoney(1L);
        return new ResponseEntity<>(getMoneyThu, HttpStatus.OK);
    }

    @GetMapping("/getMoneyChi")
    public ResponseEntity<?> getMoneyChi(){
        BigDecimal getMoneyChi = cashFlowService.getSumChoiceMoney(2L);
        return new ResponseEntity<>(getMoneyChi, HttpStatus.OK);
    }

    @GetMapping("/getMoneyNo")
    public ResponseEntity<?> getMoneyNo(){
        BigDecimal getMoneyNo = cashFlowService.getSumChoiceMoney(3L);
        return new ResponseEntity<>(getMoneyNo, HttpStatus.OK);
    }

    @GetMapping("/getMoneyAll")
    public ResponseEntity<?> getMoneyAll(){
        BigDecimal getMoneyThu = cashFlowService.getSumChoiceMoney(1L);
        BigDecimal getMoneyChi = cashFlowService.getSumChoiceMoney(2L);
        if(getMoneyChi == null){
            return new ResponseEntity<>(getMoneyThu, HttpStatus.OK);
        }
        if(getMoneyThu == null) {
            BigDecimal totalMoneyChi = getMoneyChi.add(getMoneyChi);
            return new ResponseEntity<>(getMoneyChi.subtract(totalMoneyChi), HttpStatus.OK);
        }
        BigDecimal getMoneyAll = getMoneyThu.subtract(getMoneyChi);
        return new ResponseEntity<>(getMoneyAll, HttpStatus.OK);
    }

    @GetMapping("/date_timepicker_start")
    public ResponseEntity<?>getDateStart(){
        Date dateStart = cashFlowService.getDateStart();
        if(dateStart == null) {
            return new ResponseEntity<>(new Date(), HttpStatus.OK);
        }
        return new ResponseEntity<>(dateStart, HttpStatus.OK);
    }

    @GetMapping("/date_timepicker_end")
    public ResponseEntity<?>getDateEnd(){
        Date dateEnd = cashFlowService.getDateEnd();
        if(dateEnd == null) {
            return new ResponseEntity<>(new Date(), HttpStatus.OK);
        }
        return new ResponseEntity<>(dateEnd, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?>createCashFlow(@Validated @RequestBody CashFlowStringDTO cashFlowStringDTO, BindingResult bindingResult) throws ParseException {

        if(bindingResult.hasFieldErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }

        CashFlowDTO cashFlowDTO = cashFlowStringDTO.toCashFlowDTO();

        Date date = new Date();
        Date dateTime = cashFlowDTO.getTime();

        if(dateTime.compareTo(date) > 0) {
            throw new DataInputException("Ng??y nh???p kh??ng ???????c l???n h??n ng??y hi???n t???i");
        }

        try {
            cashFlowDTO.setId(0L);
            CashFlow cashFlow = cashFlowDTO.toCashFlow();

            CashFlow newCashFlow = cashFlowService.save(cashFlow);
            return new ResponseEntity<>(newCashFlow.toCashFlowDTO(), HttpStatus.CREATED);
        } catch ( Exception e) {
            throw new DataInputException("Vui l??ng li??n h??? Admin");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?>editCashFlow(@Validated @RequestBody CashFlowStringDTO cashFlowStringDTO, BindingResult bindingResult) throws ParseException {

        if(bindingResult.hasFieldErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }
        CashFlowDTO cashFlowDTO = cashFlowStringDTO.toCashFlowDTO();

        Date date = new Date();
        Date dateTime = cashFlowDTO.getTime();

        if(dateTime.compareTo(date) > 0) {
            throw new DataInputException("Ng??y nh???p kh??ng ???????c l???n h??n ng??y hi???n t???i");
        }

        try {
            CashFlow cashFlow = cashFlowDTO.toCashFlow();
            CashFlow newCashFlow = cashFlowService.save(cashFlow);
            cashFlowDTO.setId(newCashFlow.getId());
            return new ResponseEntity<>(newCashFlow.toCashFlowDTO(), HttpStatus.CREATED);
        } catch ( Exception e) {
            throw new DataInputException("Vui l??ng li??n h??? Admin");
        }


    }

    @DeleteMapping("/suspend/{cashFlowId}")
    public ResponseEntity<?>suspend(@PathVariable Long cashFlowId){
        Optional<CashFlow> cashFlowOptional = cashFlowService.findById(cashFlowId);

        if(!cashFlowOptional.isPresent()) {
            throw new DataInputException("Th??ng tin kh??ng t??ng t???i");
        }

        try {
            cashFlowService.softDelete(cashFlowId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e){
            e.printStackTrace();
            throw new DataInputException("Vui l??ng li??n h??? Admin");
        }
    }
}
