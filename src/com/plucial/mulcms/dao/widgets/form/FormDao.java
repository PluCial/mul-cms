package com.plucial.mulcms.dao.widgets.form;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.widgets.form.FormMeta;
import com.plucial.mulcms.model.assets.Page;
import com.plucial.mulcms.model.widgets.form.Form;

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
            meta.assetsRef.equal(page.getKey())
            ).sort(new Sort(meta.createDate)).asList();
    }

}
