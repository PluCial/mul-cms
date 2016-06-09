package com.plucial.mulcms.dao.template;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.template.MailTemplateMeta;
import com.plucial.mulcms.model.template.MailTemplate;

public class MailTemplateDao extends DaoBase<MailTemplate>{
    
    /** META */
    private static final MailTemplateMeta meta = MailTemplateMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<MailTemplate> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }

}
