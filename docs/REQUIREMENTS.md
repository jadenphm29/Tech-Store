# Tech Store — Requirements

## Chức Năng Khách Hàng (Customer)

### Quản lý Sản phẩm
- Xem trang chủ
- Xem danh sách sản phẩm
- Xem chi tiết sản phẩm
- Tìm kiếm sản phẩm
- Lọc theo danh mục
- Lọc theo thương hiệu
- Lọc theo giá
- Sắp xếp sản phẩm
- Phân trang

### Xác thực & Tài khoản
- Đăng ký tài khoản
- Đăng nhập
- Đăng xuất
- Quản lý thông tin cá nhân
- Thay đổi mật khẩu

### Quản lý Địa chỉ
- Thêm địa chỉ giao hàng
- Cập nhật địa chỉ
- Xóa địa chỉ
- Đặt địa chỉ mặc định

### Giỏ Hàng
- Thêm sản phẩm vào giỏ
- Xem giỏ hàng
- Cập nhật số lượng
- Xóa sản phẩm
- Tính tổng tiền

### Đơn Hàng
- Thanh toán (COD, Bank Transfer)
- Xem danh sách đơn hàng
- Xem chi tiết đơn hàng
- Theo dõi trạng thái
- Đánh giá sản phẩm

## Chức Năng Quản trị (Admin)

### Quản lý Sản phẩm
- CRUD sản phẩm
- Quản lý hình ảnh
- Quản lý biến thể (variant)
- Quản lý SKU

### Quản lý Danh mục & Thương hiệu
- CRUD Danh mục
- CRUD Thương hiệu

### Quản lý Tồn kho
- Xem tồn kho
- Cập nhật tồn kho
- Cảnh báo hết hàng

### Quản lý Đơn hàng
- Xem danh sách đơn hàng
- Cập nhật trạng thái
- Xóa đơn hàng

### Quản lý Khách hàng
- Xem danh sách khách hàng
- Xem chi tiết khách hàng
- Khóa/mở khóa tài khoản

### Thống kê
- Xem dashboard
- Doanh thu cơ bản
- Số đơn hàng

## Quy Tắc Nghiệp Vụ (Business Rules)

1. **Tồn kho:** Phải kiểm tra trước khi đặt hàng
2. **Giá:** Luôn tính từ Database, không tin tưởng Client
3. **Đơn hàng:** Snapshot giá/tên sản phẩm tại thời điểm đặt hàng
4. **Khuyến mãi:** Áp dụng khi có mã code hợp lệ
5. **Security:** Khách hàng không thể xem đơn hàng của người khác