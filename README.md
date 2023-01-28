# SurvivorIO Download [APK](https://github.com/anhquoc2010/SurvivorIO/raw/develop/app/release/app-release.apk)
**ĐẠI HỌC ĐÀ NẴNG**

**TRƯỜNG ĐẠI HỌC SƯ PHẠM KỸ THUẬT**

![image](https://user-images.githubusercontent.com/87579077/215233944-334b1d90-c65f-417e-a458-ce304df6d1c7.png)

**BÁO CÁO**

# **LẬP TRÌNH TRÊN ĐTDĐ**

**Tên đề tài:**

# **XÂY DỰNG APP PET BOOKING**

**Sinh viên** : Hồ Quang Phúc - 2050531200250

Lê Anh Quốc - 2050531200262

Trần Đức Mạnh - 2050531200232

Trần Thị Kim Ngân - 2050531200236 Huỳnh Tấn Lực - 2050531200230

**GVHD** : ThS. Đỗ Phú Huy

_ **Đà Nẵng, tháng 12 năm 2022** _

#
# **TỔNG QUAN**

## **Lý do chọn đề tài**

Với nhu cầu sống ngày cao, con người có xu hướng tìm thú vui riêng cho mình. Nuôi thú cưng là một trong những hoạt động tinh thần cùng thể chất không thể không kể đến.

Với các bạn trẻ, sẽ chọn những con cún hoặc mèo tinh nghịch, phong cách.

Với những sản phẩm vô hình và hữu hình thú cưng mang lại. Mọi người dần chăm sóc và lo lắng cho thú cưng của mình với nhu cầu cao hơn. Đồ ăn, thức uống, khám định kỳ, spa làm đẹp,… Đi kèm theo đó thì công việc của mọi người cũng bận rộn hơn. Vậy nhu cầu chăm sóc cho thú cưng lớn.

Với xu hướng người sử dụng mạng internet để thực hiện các giao dịch trực tuyến thông qua các App hiện tại rất phổ biến. Khách hàng có thể tìm kiếm được những thông tin, trao đổi và mua bán hàng hóa ở bất cứ đâu và vào bất cứ lúc nào thông qua app trên điện thoại. Sự tiện lợi này đã thể hiện được rõ vai trò và lợi ích của website trong thời điểm tương lai khi internet đang ngày càng có tốc độ phát triển nhanh chóng vượt bậc, vì vậy thực hiện đề tài "Xây dựng App Pet Booking" là một giải pháp kinh doanh hợp lý và hứa hẹn mang về nguồn lợi nhuận lớn.

## **Mục đích đề tài**

- Số hóa quá trình kết nối giữa người nuôi và người nhận chăm sóc thú cưng
- Xây dựng môi trường chăm nuôi thú cưng lành mạnh, văn minh
- Mang lại thuận lợi cho người nuôi trong việc chăm sóc thú cưng khi có việc đột xuất hoặc ít thời gian rảnh
- Hỗ trợ kết nối người gửi/chăm sóc và thú cưng trong thời gian ngắn
- Quản lí thú cưng ở khoảng cách xa bằng GPS trong thời gian gửi
- Hỗ trợ rà soát thông tin, đánh giá người chăm sóc
- Giúp người gửi cập nhật thông tin thú cưng nhanh chóng, tạo thuận lợi cho việc tiếp cận của bên nhận chăm sóc

## **Phạm vi đề tài**

Áp dụng cho chủ thú cưng và chủ kinh doanh

#
# **SERVICE ĐƯỢC SỬ DỤNG**

1. Authentication
2. Realtime Database
3. Storage
4. Messaging

#
# **CƠ SỞ DỮ LIỆU - ERD**

##

![image](https://user-images.githubusercontent.com/87579077/215233975-bdca38d6-74be-43a7-8674-1a24458bba3f.png)

###### ERD hệ thống
#
# **GIAO DIỆN - CHỨC NĂNG**

## **Các giao diện của khách hàng**

### **Giao diện trang chủ**

- Khi người dùng lần đầu vào app, người dùng sẽ bắt gặp trang chủ của app

![image](https://user-images.githubusercontent.com/87579077/215233993-82dc388c-1944-47db-957e-5e7a1106ea67.png)![image](https://user-images.githubusercontent.com/87579077/215233996-15532b54-ac2c-4503-a3bf-635bb5de5134.png)![image](https://user-images.githubusercontent.com/87579077/215234000-23e01bcb-d283-4ad6-8c6e-aae5713a8ed7.png)![image](https://user-images.githubusercontent.com/87579077/215234007-a0c8ef20-cd92-4c56-b358-2947ded8d17d.png)
###### Giao diện Onboarding

### **Chức năng đăng ký tài khoản**

Khách vãng lai chưa có tài khoản có thể thực hiện đăng ký tài khoản để sử dụng app qua các bước sau đây:

- Sau khi đã load thành công vào app, giao diện trang chủ hiển thị

![image](https://user-images.githubusercontent.com/87579077/215234032-dcf97c66-ff3f-4680-ae62-82776f63a21b.png)
###### Giao diện trang chủ

- Khách vãng lai chưa có tài khoản sẽ click vào _ **"Sign Up"** _. Giao diện Đăng ký sẽ hiển thị. Khách vãng vai nhập đầy đủ các thông tin được yêu cầu và nhấn _ **"Sign Up"** _ để đăng ký tài khoản.

![image](https://user-images.githubusercontent.com/87579077/215234070-e529fbb2-128b-4ebe-8d37-e7ca0ac061e3.png)
###### Giao diện Đăng ký
### **Chức năng đăng nhập**

Khách hàng đã có tài khoản có thể thực hiện đăng nhập để sử dụng app qua các bước sau đây:

- Sau khi đã load thành công vào app, click chọn _ **Sign In** _ ở thanh _ **Trang chủ.** _ Nhập đầy đủ các thông tin bao gồm _ **"Tên đăng nhập"** _ và _ **"Mật khẩu"** _

![image](https://user-images.githubusercontent.com/87579077/215234122-c8d50aec-72a4-441d-8b71-6893951e80f9.png)
###### Giao diện Đăng Nhập

- Khi nhập sai mật khẩu, hệ thống sẽ thông báo lỗi. Khách hàng nhập lại mật khẩu đúng

![image](https://user-images.githubusercontent.com/87579077/215234160-d562c0c5-7eec-4feb-8f23-744e557c02c3.png)
###### Giao diện thông báo sai mật khẩu

- Nếu nhập đúng, hệ thống sẽ load vào màn hình trang chủ của người dùng.

![image](https://user-images.githubusercontent.com/87579077/215234198-9ffe68c5-101d-4170-83e2-ce3dbc43230a.png)
###### Trang chủ app
### **Chức năng đăng xuất**

Khi người dùng không còn nhu cầu sử dụng hệ thống, người dùng có thể _ **Logout** _ bằng cách: click vào button ![image](https://user-images.githubusercontent.com/87579077/215234212-7edd68c5-43b4-448b-a63f-041ea196e4b9.png) ở góc phải màn hình

![image](https://user-images.githubusercontent.com/87579077/215234232-ec03a458-6803-4d37-9f3a-08193504d4ff.png)
### **Chức năng xem danh sách được booking**

Sau khi truy cập trang chủ thành công, người dùng có thể xem được danh sách những người đã book mình chăm sóc pet

- Click vào mục _ **"Booking"** _, giao diện xem danh sách sẽ hiển thị

![image](https://user-images.githubusercontent.com/87579077/215234242-92ca758b-db49-4302-8232-faf292d96ac1.png)
###### Giao diện danh sách Bookings

- Có thể xem thông tin chi tiết của khách đã book mình bằng cách click vào item của khách đó. Giao diện xem thông tin chi tiết sẽ hiện ra.

![image](https://user-images.githubusercontent.com/87579077/215234249-77986d07-b0d5-4c62-bb76-f2e6f218cf0c.png)
###### Giao diện xem chi tiết Bookings

![image](https://user-images.githubusercontent.com/87579077/215234255-d0c1b537-f5f1-4909-b78f-00746f527a4c.png)
###### Giao diện xoá Bookings
### **Chức năng xem danh sách đã book**

Sau khi truy cập trang chủ thành công, người dùng có thể xem được danh sách những người mình đã book để chăm sóc pet

- Click vào mục _ **"Requests"** _, giao diện xem danh sách sẽ hiển thị

![image](https://user-images.githubusercontent.com/87579077/215234263-1281d3fb-df6b-4db2-9861-cd8aa0efec63.png)
###### Giao diện xem danh sách Requests

- Có thể xem thông tin chi tiết của người được book bằng cách click vào item của người đó. Giao diện xem thông tin chi tiết sẽ hiện ra.

![image](https://user-images.githubusercontent.com/87579077/215234273-a3b16527-43bc-4334-83eb-87973d29c8c9.png)
###### Giao diện xem chi tiết Requests

![image](https://user-images.githubusercontent.com/87579077/215234276-b1a19ef1-f371-4476-9418-51db6faa6e5c.png)
###### Giao diện xoá Requests
### **Chức năng nhắn tin**

![image](https://user-images.githubusercontent.com/87579077/215234280-f88b27d4-40fd-4a8e-9b90-82f5856312c0.png)
###### Giao diện nhắn tin - 1

![image](https://user-images.githubusercontent.com/87579077/215234286-e98c3918-e6a4-40f8-8329-8740294322fd.png)
###### Giao diện nhắn tin - 2

![image](https://user-images.githubusercontent.com/87579077/215234307-d6a9cf70-0f5f-42f5-9f7e-a198c7419c29.png)
###### Giao diện xoá tin nhắn

### **Chức năng xem danh sách Pets**

![image](https://user-images.githubusercontent.com/87579077/215234315-37fa0827-4a46-4b2a-92fc-e4e904cbc632.png)
###### Giao diện danh sách Pets

![image](https://user-images.githubusercontent.com/87579077/215234332-ce86e4c9-81fa-44fa-ba91-dac3656f88bd.png)![image](https://user-images.githubusercontent.com/87579077/215234343-e41cf947-4be3-4472-8560-04aeb75324ec.png)
###### Giao diện xem chi tiết Pets

![image](https://user-images.githubusercontent.com/87579077/215234355-33812762-ca83-48b9-810e-13643f1d720d.png)
###### Giao diện xoá Pets
### **Chức năng xem danh sách người gửi thú cưng**

![image](https://user-images.githubusercontent.com/87579077/215234371-b780a844-acf6-4cd9-834f-390600a6cd84.png)
###### Giao diện xem danh sách người gửi thú cưng

![image](https://user-images.githubusercontent.com/87579077/215234380-2c98e2c9-179c-40a4-b4bd-572bcc758c6c.png)![image](https://user-images.githubusercontent.com/87579077/215234386-cae47a22-f37e-4497-82bb-ed475ff24b9d.png)
###### Giao diện xem chi tiết thông tin người giữ thú cưng
### **Chức năng cập nhật thông tin cá nhân**

Chức năng này chỉ hiện khi người dùng đăng nhập thành công hệ thống

![image](https://user-images.githubusercontent.com/87579077/215234400-384e3cb4-22e0-4275-8461-47f0ea2b0773.png)
###### Người dùng chọn biểu tượng thông tin cá nhân

Người dùng chọn _ **Edit profile** _ để cập nhật thông tin cá nhân. Người dùng điền đầy đủ các thông tin muốn cập nhật và bấm Update

![image](https://user-images.githubusercontent.com/87579077/215234409-31471af1-c4a0-4f5f-9b09-79f82fc06918.png)
###### Giao diện edit thông tin cá nhân

![image](https://user-images.githubusercontent.com/87579077/215234412-2a64a1ba-01c2-4c0d-8e9d-3224173d3b02.png)
###### Update thành công thông tin cá nhân
