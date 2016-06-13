package com.plucial.mulcms.dao.form;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.plucial.mulcms.meta.form.FormControlMeta;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.form.FormControl;

public class FormControlDao extends DaoBase<FormControl>{
    
    /** META */
    private static final FormControlMeta meta = FormControlMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<FormControl> getList(Form form) {
        return  Datastore.query(meta).filter(
            meta.formRef.equal(form.getKey())
                ).asList();
    }

}
