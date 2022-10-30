class App {

    static DOMAIN_API = "http://localhost:8080";

    static BASE_URL_USER = this.DOMAIN_API + "/api/users";
    static BASE_URL_LOGIN = this.DOMAIN_API + "/api/auth";
    static BASE_URL_PRODUCT = this.DOMAIN_API + "/api/products";

    static AlertMessageEn = class {
        static SUCCESS_CREATED = "Successful data generation !";
        static SUCCESS_UPDATED = "Data update successful !";
        static SUCCESS_DEPOSIT = "Deposit transaction successful !";
        static SUCCESS_WITHDRAW = "Withdrawal transaction successful !";
        static SUCCESS_TRANSFER = "Transfer transaction successful !";
        static SUCCESS_DEACTIVATE = "Deactivate the customer successfully !";

        static ERROR_400 = "The operation failed, please check the data again.";
        static ERROR_401 = "Unauthorized - Your access token has expired or is not valid.";
        static ERROR_403 = "Forbidden - You are not authorized to access this resource.";
        static ERROR_404 = "Not Found - The resource has been removed or does not exist";
        static ERROR_500 = "Internal Server Error - The server system is having problems or cannot be accessed.";

        static ERROR_LOADING_PROVINCE = "Loading list of provinces - cities failed !";
        static ERROR_LOADING_DISTRICT = "Loading list of district - ward failed !"
        static ERROR_LOADING_WARD = "Loading list of wards - communes - towns failed !";
    }

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_DEPOSIT = "Giao dịch gửi tiền thành công !";
        static SUCCESS_WITHDRAW = "Giao dịch rút tiền thành công !";
        static SUCCESS_TRANSFER = "Giao dịch chuyển khoản thành công !";
        static SUCCESS_DEACTIVATE = "Hủy kích hoạt khách hàng thành công !";

        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

        static ERROR_LOADING_PROVINCE = "Tải danh sách tỉnh - thành phố không thành công !";
        static ERROR_LOADING_DISTRICT = "Tải danh sách quận - phường - huyện không thành công !";
        static ERROR_LOADING_WARD = "Tải danh sách phường - xã - thị trấn không thành công !";
    }

    static SweetAlert = class {

        static showAlertSuccess(t) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: t,
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showAlertError(t) {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: t,
                showConfirmButton: true
            })
        }

        static showSuspendedConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to suspend the selected customer ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please suspend this client !',
                cancelButtonText: 'Cancel',
            })
        }
    }

    static IziToast = class {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
    }

    static renderRowUser(obj) {
        let str = `
            <tr id="tr_${obj.id}">
                <td>${obj.id}</td>
                <td>${obj.fullName}</td>
                <td>${obj.username}</td>
                <td class="text-center">${obj.phone}</td>
                <td>${obj.address}</td>
                <td class="text-end num-space">${obj.role.code}</td>
                <td class="text-center">
                    <a class="btn btn-outline-secondary edit" data-id="${obj.id}" title="" data-bs-toggle="tooltip"  data-bs-original-title="Edit">
                        <i class="fas fa-edit"></i>
                    </a>
                </td>
                <td class="text-center">
                    <a class="btn btn-outline-danger delete" data-id="${obj.id}" title="" data-bs-toggle="tooltip" data-bs-original-title="Delete">
                        <i class="fas fa-trash-alt"></i>
                    </a>
                </td>
            </tr>
        `;

        return str;
    }

    static renderRowProduct(obj) {
        let str = `
            <tr id="tr_${obj.id}">
                <td>${obj.id}</td>
                <td>${obj.name}</td>
                <td>${obj.amount}</td>
                <td class="text-center">${obj.price}</td>
                <td>${obj.description}</td>
                <td class="text-end num-space">${obj.avatar}</td>
                <td class="text-center">
                    <a class="btn btn-outline-secondary edit" data-id="${obj.id}" title="" data-bs-toggle="tooltip"  data-bs-original-title="Edit">
                        <i class="fas fa-edit"></i>
                    </a>
                </td>
                <td class="text-center">
                    <a class="btn btn-outline-danger delete" data-id="${obj.id}" title="" data-bs-toggle="tooltip" data-bs-original-title="Delete">
                        <i class="fas fa-trash-alt"></i>
                    </a>
                </td>
            </tr>
        `;

        return str;
    }
}

class User {
    constructor(id, fullName, username, phone, address, role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
class Product {
    constructor(id, name, amount, price, description, avatar) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.description =description;
        this.avatar = avatar;
    }
}



