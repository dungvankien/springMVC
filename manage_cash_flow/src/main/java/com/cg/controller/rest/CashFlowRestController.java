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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @PostMapping("/create")
    public ResponseEntity<?>createCashFlow(@RequestBody CashFlowStringDTO cashFlowStringDTO) throws ParseException {

        CashFlowDTO cashFlowDTO = cashFlowStringDTO.toCashFlowDTO();

        cashFlowDTO.setId(0L);
        CashFlow cashFlow = cashFlowDTO.toCashFlow();

        CashFlow newCashFlow = cashFlowService.save(cashFlow);
        return new ResponseEntity<>(newCashFlow.toCashFlowDTO(), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?>editCashFlow( @RequestBody CashFlowStringDTO cashFlowStringDTO) throws ParseException {

        CashFlowDTO cashFlowDTO = cashFlowStringDTO.toCashFlowDTO();

        CashFlow cashFlow = cashFlowDTO.toCashFlow();
        CashFlow newCashFlow = cashFlowService.save(cashFlow);
        cashFlowDTO.setId(newCashFlow.getId());
        return new ResponseEntity<>(newCashFlow.toCashFlowDTO(), HttpStatus.CREATED);
    }

    @DeleteMapping("/suspend/{cashFlowId}")
    public ResponseEntity<?>suspend(@PathVariable Long cashFlowId){
        Optional<CashFlow> cashFlowOptional = cashFlowService.findById(cashFlowId);

        if(!cashFlowOptional.isPresent()) {
            throw new DataInputException("Thông tin không tông tại");
        }

        try {
            cashFlowService.softDelete(cashFlowId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e){
            e.printStackTrace();
            throw new DataInputException("Vui lòng liên hệ Admin");
        }
    }
}