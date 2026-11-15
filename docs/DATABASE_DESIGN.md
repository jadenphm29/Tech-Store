# Database Design — Tech Store

## Database Name
`tech_store`

## Core Tables

### 1. users
Lưu thông tin người dùng

| Trường | Kiểu | Ràng buộc | Ghi chú |
|---|---|---|---|
| id | BIGINT | PK, AUTO_INCREMENT | |
| full_name | VARCHAR(255) | NOT NULL | |
| email | VARCHAR(255) | UNIQUE, NOT NULL | |
| password | VARCHAR(255) | NOT NULL | BCrypt |
| phone | VARCHAR(20) | | |
| status | ENUM | DEFAULT 'ACTIVE' | ACTIVE, INACTIVE |
| created_at | TIMESTAMP | DEFAULT NOW() | |
| updated_at | TIMESTAMP | DEFAULT NOW() | |

### 2. roles
Quản lý vai trò

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| name | VARCHAR(50) | UNIQUE |

**Data:**
- ROLE_CUSTOMER
- ROLE_ADMIN

### 3. user_roles
Liên kết User và Role (Many-to-Many)

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| user_id | BIGINT | PK, FK → users.id |
| role_id | BIGINT | PK, FK → roles.id |

### 4. categories
Danh mục sản phẩm

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| name | VARCHAR(100) | NOT NULL |
| slug | VARCHAR(100) | UNIQUE |
| description | TEXT | |
| status | ENUM | ACTIVE, INACTIVE |

### 5. brands
Thương hiệu

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| name | VARCHAR(100) | NOT NULL, UNIQUE |
| description | TEXT | |
| logo_url | VARCHAR(255) | |

### 6. products
Sản phẩm

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| name | VARCHAR(255) | NOT NULL |
| slug | VARCHAR(255) | UNIQUE |
| description | TEXT | |
| category_id | BIGINT | FK → categories.id |
| brand_id | BIGINT | FK → brands.id |
| thumbnail | VARCHAR(255) | |
| status | ENUM | ACTIVE, INACTIVE |
| created_at | TIMESTAMP | DEFAULT NOW() |
| updated_at | TIMESTAMP | DEFAULT NOW() |

### 7. product_variants
Phiên bản sản phẩm

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| product_id | BIGINT | FK → products.id |
| sku | VARCHAR(50) | UNIQUE |
| color | VARCHAR(50) | |
| storage | VARCHAR(50) | |
| ram | VARCHAR(50) | |
| price | DECIMAL(10,2) | NOT NULL |
| original_price | DECIMAL(10,2) | |
| stock_quantity | INT | DEFAULT 0 |
| status | ENUM | ACTIVE, INACTIVE |

### 8. product_images
Hình ảnh sản phẩm

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| product_id | BIGINT | FK → products.id |
| image_url | VARCHAR(255) | NOT NULL |
| display_order | INT | |

### 9. carts
Giỏ hàng

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| user_id | BIGINT | FK → users.id |
| created_at | TIMESTAMP | DEFAULT NOW() |
| updated_at | TIMESTAMP | DEFAULT NOW() |

### 10. cart_items
Sản phẩm trong giỏ

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| cart_id | BIGINT | FK → carts.id |
| variant_id | BIGINT | FK → product_variants.id |
| quantity | INT | NOT NULL |

### 11. addresses
Địa chỉ giao hàng

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| user_id | BIGINT | FK → users.id |
| recipient_name | VARCHAR(255) | NOT NULL |
| phone | VARCHAR(20) | NOT NULL |
| address_line | VARCHAR(255) | NOT NULL |
| ward | VARCHAR(100) | |
| district | VARCHAR(100) | |
| city | VARCHAR(100) | |
| is_default | BOOLEAN | DEFAULT FALSE |

### 12. orders
Đơn hàng

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| user_id | BIGINT | FK → users.id |
| address_id | BIGINT | FK → addresses.id |
| order_code | VARCHAR(50) | UNIQUE |
| total_amount | DECIMAL(10,2) | |
| shipping_fee | DECIMAL(10,2) | DEFAULT 0 |
| discount_amount | DECIMAL(10,2) | DEFAULT 0 |
| final_amount | DECIMAL(10,2) | |
| payment_method | ENUM | COD, BANK_TRANSFER |
| payment_status | ENUM | PENDING, PAID, FAILED |
| order_status | ENUM | PENDING, CONFIRMED, PROCESSING, SHIPPING, DELIVERED, CANCELLED |
| created_at | TIMESTAMP | DEFAULT NOW() |
| updated_at | TIMESTAMP | DEFAULT NOW() |

### 13. order_details
Chi tiết đơn hàng

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| order_id | BIGINT | FK → orders.id |
| variant_id | BIGINT | FK → product_variants.id |
| product_name_snapshot | VARCHAR(255) | Snapshot tên |
| sku_snapshot | VARCHAR(50) | Snapshot SKU |
| unit_price | DECIMAL(10,2) | Giá tại thời điểm đặt hàng |
| quantity | INT | |
| subtotal | DECIMAL(10,2) | |

### 14. promotions
Khuyến mãi

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| code | VARCHAR(50) | UNIQUE |
| discount_type | ENUM | PERCENTAGE, FIXED |
| discount_value | DECIMAL(10,2) | |
| start_date | TIMESTAMP | |
| end_date | TIMESTAMP | |
| minimum_order_amount | DECIMAL(10,2) | |
| usage_limit | INT | |
| status | ENUM | ACTIVE, INACTIVE |

### 15. reviews
Đánh giá sản phẩm

| Trường | Kiểu | Ràng buộc |
|---|---|---|
| id | BIGINT | PK |
| user_id | BIGINT | FK → users.id |
| product_id | BIGINT | FK → products.id |
| rating | INT | 1-5 |
| comment | TEXT | |
| created_at | TIMESTAMP | DEFAULT NOW() |

## ERD — Entity Relationship Diagram

\`\`\`mermaid
erDiagram
USERS ||--o{ USER_ROLES : has
ROLES ||--o{ USER_ROLES : has
USERS ||--o{ CARTS : owns
USERS ||--o{ ORDERS : places
USERS ||--o{ ADDRESSES : has
USERS ||--o{ REVIEWS : writes

    CATEGORIES ||--o{ PRODUCTS : contains
    BRANDS ||--o{ PRODUCTS : manufactures
    PRODUCTS ||--o{ PRODUCT_VARIANTS : has
    PRODUCTS ||--o{ PRODUCT_IMAGES : has
    PRODUCTS ||--o{ REVIEWS : receives
    
    PRODUCT_VARIANTS ||--o{ CART_ITEMS : in
    PRODUCT_VARIANTS ||--o{ ORDER_DETAILS : in
    
    CARTS ||--o{ CART_ITEMS : contains
    
    ADDRESSES ||--o{ ORDERS : receives
    ORDERS ||--o{ ORDER_DETAILS : contains
    
    PROMOTIONS ||--o{ ORDERS : applied
\`\`\`

## Ràng Buộc Chính

1. **Primary Key:** Mỗi bảng có `id BIGINT AUTO_INCREMENT`
2. **Foreign Key:** Tất cả quan hệ được bảo vệ bằng FK
3. **Unique Constraint:** email, SKU, order_code
4. **Index:** Trên các trường thường tìm kiếm (email, slug, etc.)
5. **Không lưu tiền bằng FLOAT/DOUBLE:** Sử dụng DECIMAL(10,2)
6. **Timestamps:** created_at, updated_at cho audit

## Design Rules

- Sử dụng Snapshot cho order_details (giữ nguyên dữ liệu lịch sử)
- Không xóa đơn hàng (soft delete nếu cần)
- Tồn kho được quản lý ở product_variants
- Cart được reset khi đặt hàng thành công