package dao;

import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public List<Integer> getAllReceiptsIDs(String tagName) {
        return dsl.selectFrom(TAGS).where(TAGS.TAG.eq(tagName)).fetch(TAGS.ID);
    }

    public void insert(String tagName, int id) {
        TagsRecord tagRecord = dsl
                .selectFrom(TAGS)
                .where(TAGS.ID.eq(id))
                .fetchAny();

        if (tagRecord == null) {
            tagRecord = dsl
                    .insertInto(TAGS, TAGS.ID, TAGS.TAG)
                    .values(id, tagName)
                    .returning(TAGS.ID)
                    .fetchOne();

            checkState(tagRecord != null && tagRecord.getId() != null, "Insert failed");
        } else {
            tagRecord.delete();
        }
    }
}
