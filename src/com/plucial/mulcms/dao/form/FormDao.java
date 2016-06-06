package com.plucial.mulcms.dao.form;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.form.FormMeta;
import com.plucial.mulcms.model.Page;
import com.plucial.mulcms.model.form.Form;

public class FormDao extends DaoBase<Form>{
    
    /** META */
    private static final FormMeta meta = FormMeta.get();
    
    /**
     * リストの取得
     * @return
     */
    public List<Form> getList() {
        return  Datastore.query(meta)
                    .sort(new Sort(meta.createDate)).asList();
    }
    
    /**
     * リストの取得
     * @return
     */
    public List<Form> getList(Page page) {
        return  Datastore.query(meta).filter(
            meta.pageRef.equal(page.getKey())
            ).asList();
    }

}
