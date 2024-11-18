$(document).ready(function () {
    // Hiển thị thông tin người dùng đã đăng nhập thành công
    $.ajax({
        type: 'GET',
        url: '/users/me', // Endpoint để lấy thông tin người dùng
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            var json = JSON.stringify(data, null, 4); // Chuyển đổi dữ liệu JSON thành chuỗi
            $('#profile').html(data.fullName); // Hiển thị thông tin người dùng tại phần tử có id="profile"
            $('#images').attr('src', data.images); // Đặt ảnh vào phần tử có id="images"
        },
        error: function (e) {
            var json = e.responseText;
            alert('Sorry, you are not logged in.'); // Hiển thị lỗi nếu không đăng nhập
        }
    });

    // Hàm đăng nhập
    $('#Login').click(function () {
        var email = document.getElementById('email').value; // Lấy email từ input
        var password = document.getElementById('password').value; // Lấy password từ input
        var basicInfo = JSON.stringify({ email: email, password: password }); // Tạo object JSON

        $.ajax({
            type: 'POST',
            url: '/auth/login', // Endpoint đăng nhập
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: basicInfo,
            success: function (data) {
                localStorage.token = data.token; // Lưu token vào localStorage
                window.location.href = '/user/profile'; // Điều hướng tới trang profile
            },
            error: function () {
                alert('Login Failed'); // Hiển thị lỗi khi đăng nhập thất bại
            }
        });
    });

    // Hàm đăng xuất
    $('#Logout').click(function () {
        localStorage.clear(); // Xóa localStorage
        window.location.href = '/login'; // Điều hướng về trang login
    });
});
