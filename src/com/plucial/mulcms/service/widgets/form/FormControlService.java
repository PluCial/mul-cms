package com.plucial.mulcms.service.widgets.form;

import java.util.List;
import java.util.UUID;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.plucial.gae.global.exception.ObjectNotExistException;
import com.plucial.mulcms.dao.widgets.form.FormControlDao;
import com.plucial.mulcms.meta.widgets.form.FormControlMeta;
import com.plucial.mulcms.model.widgets.form.Form;
import com.plucial.mulcms.model.widgets.form.FormControl;


public class FormControlService {
    
    /** DAO */
    private static final FormControlDao dao = new FormControlDao();
    
    /**
     * 取得
     * @param keyString
     * @return
     * @throws ObjectNotExistException 
     */
    public static FormControl get(String keyString) throws ObjectNotExistException {
        FormControl model = dao.getOrNull(createKey(keyString));
        if(model == null) throw new ObjectNotExistException();
        return model;
    }
    
    /**
     * 追加
     * @param form
     * @param controlName
     * @param required
     * @param transFlg
     * @return
     */
    public static FormControl put(Form form, String controlName, boolean required, boolean transFlg) {
        FormControl model = new FormControl();
        model.setKey(createKey());
        model.setControlName(controlName);
        model.getFormRef().setModel(form);
        model.setRequired(required);
        model.setTransFlg(transFlg);
        
        dao.put(model);
        
        return model;
    }
    
    /**
     * リストの取得
     * @return
     */
    public static List<FormControl> getList(Form form) {
        return dao.getList(form);
    }
    
    /**
     * 更新
     * @param model
     */
    public static void update(FormControl model) {
        dao.put(model);
    }
    
    /**
     * 削除
     * @param keyString
     */
    public static void delete(String keyString) {
        dao.delete(createKey(keyString));
    }
    
    // ----------------------------------------------------------------------
    // キーの作成
    // ----------------------------------------------------------------------
    /**
     * キーの作成
     * @param keyString
     * @return
     */
    protected static Key createKey(String keyString) {
        return Datastore.createKey(FormControlMeta.get(), keyString);
    }
    
    /**
     * キーの作成
     * @return
     */
    protected static Key createKey() {
        // キーを乱数にする
        UUID uniqueKey = UUID.randomUUID();
        return createKey(uniqueKey.toString());
    }

}
