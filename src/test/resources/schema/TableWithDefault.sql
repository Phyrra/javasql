CREATE TABLE [dbo].[tblTable](
	[uidId] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblTable_uidId] DEFAULT (newsequentialid()),
	[sName] [varchar](50) NOT NULL,
	[sLastName] [varchar](50) NULL,
 CONSTRAINT [PK_tblTable] PRIMARY KEY CLUSTERED
(
	[uidId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]