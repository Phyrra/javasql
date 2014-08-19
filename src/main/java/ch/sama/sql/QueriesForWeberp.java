package ch.sama.sql;

import ch.sama.sql.dialect.tsql.TSqlQueryFactory;
import ch.sama.sql.query.base.QueryFactory;
import ch.sama.sql.query.helper.Condition;

public class QueriesForWeberp {
	public static void main(String[] args) {
        QueryFactory fac = new TSqlQueryFactory();

        int userId = 1;

        /*
        WITH PART_TABLE AS (
           SELECT PART_ID, TABLE_NAME FROM META_WEB_DESKTOP_PART UNION ALL
           SELECT PART_ID, TABLE_NAME FROM META_WEB_DESKTOP_TRANSLATION
        ), PERMS AS (
           SELECT PRG_ID, PRG_PERM_ID FROM SYS_GROUP_PERMS WHERE GRP_ID IN (
               SELECT GRP_ID FROM SYS_USER_GROUP WHERE USR_ID = 1
           )
           UNION ALL
           SELECT PRG_ID, PRG_PERM_ID FROM SYS_USER_PERMS WHERE USR_ID = 1
        )
        SELECT * FROM META_WEB_DESKTOP AS [D]
        WHERE NOT EXISTS (
           SELECT NULL FROM META_WEB_DESKTOP_PART AS [P]
           WHERE [P].DESKTOP_ID = [D].DESKTOP_ID AND EXISTS (
               SELECT NULL FROM PART_TABLE AS [T]
               WHERE [T].PART_ID = [P].PART_ID AND [T].TABLE_NAME IS NOT NULL AND [T].TABLE_NAME NOT IN (
                   SELECT SYS_PROG.PRG_NAME FROM PERMS
                   JOIN SYS_PROG ON PERMS.PRG_ID = SYS_PROG.PRG_ID AND SYS_PROG.PRG_TYPE = 'D'
                   WHERE PERMS.PRG_PERM_ID = 0
               )
           )
        ) AND VISIBLE = 1
        */
		String q1 = fac.create()
                .with("PART_TABLE").as(
                        fac.create()
                                .select(fac.field("PART_ID"), fac.field("TABLE_NAME")).from(fac.table("META_WEB_DESKTOP_PART")).union()
                                .select(fac.field("PART_ID"), fac.field("TABLE_NAME")).from(fac.table("META_WEB_DESKTOP_TRANSLATION"))
                )
                .with("PERMS").as(
                        fac.create()
                                .select(fac.field("PRG_ID"), fac.field("PRG_PERM_ID")).from(fac.table("SYS_GROUP_PERMS"))
                                .where(Condition.in(
                                        fac.field("GRP_ID"),
                                        fac.create()
                                                .select(fac.field("GRP_ID")).from(fac.table("SYS_USER_GROUP"))
                                                .where(Condition.eq(fac.field("USR_ID"), fac.numeric(userId)))
                                )).union()
                                .select(fac.field("PRG_ID"), fac.field("PRG_PERM_ID")).from(fac.table("SYS_USER_PERMS"))
                                .where(Condition.eq(fac.field("USR_ID"), fac.numeric(userId)))
                )
                .select(fac.value("*")).from(fac.table("META_WEB_DESKTOP").as("[D]"))
                .where(
                        Condition.and(
                                Condition.not(Condition.exists(
                                        fac.create()
                                                .select(fac.value("NULL")).from(fac.table("META_WEB_DESKTOP_PART").as("[P]"))
                                                .where(
                                                        Condition.and(
                                                                Condition.eq(fac.field("[P]", "DESKTOP_ID"), fac.field("[D]", "DESKTOP_ID")),
                                                                Condition.exists(
                                                                        fac.create()
                                                                                .select(fac.value("NULL")).from(fac.table("PART_TABLE").as("[T]"))
                                                                                .where(
                                                                                        Condition.and(
                                                                                                Condition.eq(fac.field("[T]", "PART_ID"), fac.field("[P]", "PART_ID")),
                                                                                                Condition.and(
                                                                                                        Condition.not(Condition.isNull(fac.field("[T]", "TABLE_NAME"))),
                                                                                                        Condition.not(Condition.in(
                                                                                                                fac.field("[T]", "TABLE_NAME"),
                                                                                                                fac.create()
                                                                                                                        .select(fac.field("SYS_PROG", "PRG_NAME")).from(fac.table("PERMS"))
                                                                                                                        .join(fac.table("SYS_PROG")).on(Condition.and(
                                                                                                                        Condition.eq(fac.field("PERMS", "PRG_ID"), fac.field("SYS_PROG", "PRG_ID")),
                                                                                                                        Condition.eq(fac.field("SYS_PROG", "PRG_TYPE"), fac.string("D"))
                                                                                                                ))
                                                                                                        ))
                                                                                                )
                                                                                        )
                                                                                )
                                                                )
                                                        )
                                                )
                                )),
                                Condition.eq(fac.field("VISIBLE"), fac.numeric(1))
                        )
                )
        .toString();

        System.out.println(q1);
	}
}