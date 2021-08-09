USE [master]
GO
/****** Object:  Database [UserManagement]    Script Date: 6/20/2021 9:14:49 PM ******/
CREATE DATABASE [UserManagement]
GO
USE [UserManagement]
GO
/****** Object:  Table [dbo].[PromotionHistory]    Script Date: 6/20/2021 9:14:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PromotionHistory](
	[promotionHistoryID] [nvarchar](20) NOT NULL,
	[date] [datetime] NOT NULL,
	[rank] [float] NOT NULL,
	[status] [nvarchar](20) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_PromotionHistory] PRIMARY KEY CLUSTERED 
(
	[promotionHistoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/20/2021 9:14:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [nvarchar](20) NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 6/20/2021 9:14:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[userID] [nvarchar](50) NOT NULL,
	[userName] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](20) NOT NULL,
	[image] [nvarchar](50) NOT NULL,
	[password] [nvarchar](200) NOT NULL,
	[status] [nvarchar](20) NOT NULL,
	[statusPromotion] [nvarchar](20) NOT NULL,
	[dateCreate] [datetime] NOT NULL,
	[rank] [float] NOT NULL,
	[roleID] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'atf3q', CAST(N'2021-06-12T20:14:38.000' AS DateTime), 3, N'Updated', N'lam')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'AxQmT', CAST(N'2021-06-12T20:14:46.000' AS DateTime), 5, N'Added', N'nguyen')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'fepA5', CAST(N'2021-06-15T08:49:48.000' AS DateTime), 5, N'Added', N'loc')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'jzyOj', CAST(N'2021-06-19T20:22:38.000' AS DateTime), 5, N'Added', N'lam')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'KE5d7', CAST(N'2021-06-19T18:49:14.000' AS DateTime), 6, N'Updated', N'hong')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'Nehrr', CAST(N'2021-06-12T20:14:37.000' AS DateTime), 7, N'Updated', N'hong')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'OMihy', CAST(N'2021-06-12T20:14:57.000' AS DateTime), 4, N'Updated', N'lam')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'QiMb7', CAST(N'2021-06-12T20:14:21.000' AS DateTime), 5, N'Added', N'lam')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'rupXc', CAST(N'2021-06-12T20:15:12.000' AS DateTime), 0, N'Deleted', N'lam')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'TT4ZW', CAST(N'2021-06-15T08:48:32.000' AS DateTime), 8, N'Updated', N'hung')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'UdtdZ', CAST(N'2021-06-12T20:14:57.000' AS DateTime), 7, N'Updated', N'nguyen')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'W1uAS', CAST(N'2021-06-12T20:14:11.000' AS DateTime), 5, N'Added', N'hong')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'WuTuJ', CAST(N'2021-06-15T08:48:21.000' AS DateTime), 5, N'Added', N'hung')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'X1qrD', CAST(N'2021-06-15T08:48:48.000' AS DateTime), 0, N'Deleted', N'nguyen')
GO
INSERT [dbo].[PromotionHistory] ([promotionHistoryID], [date], [rank], [status], [userID]) VALUES (N'zawrn', CAST(N'2021-06-15T08:49:29.000' AS DateTime), 0, N'Deleted', N'hung')
GO
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (N'A', N'Admin')
GO
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (N'S', N'Sub')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'chien', N'Phạm Thế Chiến', N'chien@gmail.com', N'0954148256', N'chien.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-06-03T09:47:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'hong', N'Nguyễn Thị Hồng', N'hong@gmail.com', N'0987456321', N'hong.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'True', CAST(N'2021-06-01T12:31:00.000' AS DateTime), 6, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'hung', N'Nguyễn Hưng', N'hung@gmail.com', N'0958761123', N'hung.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-06-01T16:23:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'huynh', N'Nguyễn Huỳnh', N'huynh@gmail.com', N'0989542344', N'huynh.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-05-30T07:15:00.000' AS DateTime), 0, N'A')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'khanh', N'Lê Quang Khánh', N'khanh@gmail.com', N'0958465412', N'khanh.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-06-02T08:27:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'ky', N'Lý Nhã Kỳ', N'ky@gmail.com', N'0965648254', N'ky.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Inactive', N'False', CAST(N'2021-05-29T19:03:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'lam', N'Đặng Văn Lâm', N'lam@gmail.com', N'0957812345', N'lam.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'True', CAST(N'2021-05-28T20:11:00.000' AS DateTime), 5, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'lan', N'Hà Lan', N'lan@gmali.com', N'0954813456', N'lan.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-05-27T08:34:00.000' AS DateTime), 0, N'A')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'loc', N'Đào Bá Lộc', N'loc@gmail.com', N'0957345687', N'loc.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'True', CAST(N'2021-06-01T12:14:00.000' AS DateTime), 5, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'nguyen', N'Nguyễn Lộc Nguyên', N'nguyen@gmail.com', N'0957134568', N'nguyen.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-05-29T13:38:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'tai', N'Phạm Tài', N'tai@gmail.com', N'0123456987', N'tai.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-05-27T07:59:00.000' AS DateTime), 0, N'A')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'thien', N'Nguyễn Quốc Thiện', N'thien@gmail.com', N'0952481232', N'thien.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-06-02T06:48:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'tuan', N'Bùi Anh Tuấn', N'tuan@gmail.com', N'0958765154', N'tuan.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-05-30T20:56:00.000' AS DateTime), 0, N'S')
GO
INSERT [dbo].[Users] ([userID], [userName], [email], [phone], [image], [password], [status], [statusPromotion], [dateCreate], [rank], [roleID]) VALUES (N'vinh', N'Lê Quang Vinh', N'vinh@gmail.com', N'0123456789', N'vinh.jpg', N'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', N'Active', N'False', CAST(N'2021-05-26T16:29:00.000' AS DateTime), 0, N'A')
GO
ALTER TABLE [dbo].[PromotionHistory]  WITH CHECK ADD  CONSTRAINT [FK_PromotionHistory_User] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[PromotionHistory] CHECK CONSTRAINT [FK_PromotionHistory_User]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_User_Role]
GO
USE [master]
GO
ALTER DATABASE [UserManagement] SET  READ_WRITE 
GO
