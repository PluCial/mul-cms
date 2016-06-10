package com.plucial.mulcms.dao.form;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Sort;

import com.plucial.mulcms.meta.form.ReceptionMailActionMeta;
import com.plucial.mulcms.model.form.Form;
import com.plucial.mulcms.model.form.ReceptionMailAction;

public class ReceptionMailActionDao extends DaoBase<ReceptionMailAction>{
    
    /** META */
    private static final ReceptionMailActionMeta meta = ReceptionMailActionMeta.get();

    /**
     * リストの取得
     * @return
     */
    public List<ReceptionMailAction> getList(Form form) {
        return  Datastore.query(meta)
                .filter(
                    meta.formRef.equal(form.getKey())
                    ).sort(new Sort(meta.createDate)).asList();
    }
}
