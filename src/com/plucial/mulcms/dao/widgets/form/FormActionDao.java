package com.plucial.mulcms.dao.widgets.form;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.widgets.form.FormActionMeta;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormAction;

public class FormActionDao extends DaoBase<FormAction>{
    
    /** META */
    private static final FormActionMeta meta = FormActionMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<FormAction> getList(Form form) {
        return  Datastore.query(meta)
                .filter(
                    meta.formRef.equal(form.getKey())
                    ).sort(new Sort(meta.createDate)).asList();
    }

}
