CREATE TABLE [dbo].[tblTable1](
	[uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable1_uidId] DEFAULT (newsequentialid()),
	[dtDate] [datetime] NULL,
	[iInt] [int] NULL,
	[fFloat] [float] NULL,
	[sString] [nvarchar](50) NULL CONSTRAINT [DF_tblTable1_sString] DEFAULT (N'Hello World'),
	CONSTRAINT [PK_tblTable1] PRIMARY KEY CLUSTERED (
		[uidId] ASC
	)
) ON [PRIMARY]

CREATE TABLE [dbo].[tblTable2](
	[uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable2_uidId] DEFAULT (newsequentialid()),
	[iInt] [int] NULL,
	[iInt2] [int] NULL,
	[dtDate] [datetime] NULL,
	[fFloat] [float] NULL,
	[sString] [varchar](100) NULL,
	CONSTRAINT [PK_tblTable2] PRIMARY KEY CLUSTERED (
		[uidId] ASC
	)
) ON [PRIMARY]

CREATE TABLE [dbo].[tblTable4](
	[uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable3_uidId] DEFAULT (newsequentialid()),
	[iInt] [int] NULL,
	[dtDate] [datetime] NULL,
	[fFloat] [float] NULL,
	[sString] [nvarchar](50) NULL,
	CONSTRAINT [PK_tblTable3] PRIMARY KEY CLUSTERED (
		[uidId] ASC
	)
) ON [PRIMARY]