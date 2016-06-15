package com.plucial.mulcms.dao.widgets.form;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.widgets.form.MailActionMeta;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.MailAction;

public class MailActionDao extends DaoBase<MailAction>{
    
    /** META */
    private static final MailActionMeta meta = MailActionMeta.get();

    /**
     * リストの取得
     * @return
     */
    public List<MailAction> getList(Form form) {
        return  Datastore.query(meta)
                .filter(
                    meta.formRef.equal(form.getKey())
                    ).sort(new Sort(meta.createDate)).asList();
    }
}
