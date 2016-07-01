package com.plucial.mulcms.meta.widgets.form;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-07-06 01:11:22")
/** */
public final class FormActionMeta extends org.slim3.datastore.ModelMeta<com.plucial.mulcms.model.widgets.form.FormAction> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, java.util.Date> createDate = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, java.util.Date>(this, "createDate", "createDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, org.slim3.datastore.ModelRef<com.plucial.mulcms.model.widgets.form.Form>, com.plucial.mulcms.model.widgets.form.Form> formRef = new org.slim3.datastore.ModelRefAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, org.slim3.datastore.ModelRef<com.plucial.mulcms.model.widgets.form.Form>, com.plucial.mulcms.model.widgets.form.Form>(this, "formRef", "formRef", org.slim3.datastore.ModelRef.class, com.plucial.mulcms.model.widgets.form.Form.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, java.util.Date> updateDate = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, java.util.Date>(this, "updateDate", "updateDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<com.plucial.mulcms.model.widgets.form.FormAction, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final org.slim3.datastore.CreationDate slim3_createDateAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationDate slim3_updateDateAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final FormActionMeta slim3_singleton = new FormActionMeta();

    /**
     * @return the singleton
     */
    public static FormActionMeta get() {
       return slim3_singleton;
    }

    /** */
    public FormActionMeta() {
        super("FormAction", com.plucial.mulcms.model.widgets.form.FormAction.class);
    }

    @Override
    public com.plucial.mulcms.model.widgets.form.FormAction entityToModel(com.google.appengine.api.datastore.Entity entity) {
        com.plucial.mulcms.model.widgets.form.FormAction model = new com.plucial.mulcms.model.widgets.form.FormAction();
        model.setCreateDate((java.util.Date) entity.getProperty("createDate"));
        if (model.getFormRef() == null) {
            throw new NullPointerException("The property(formRef) is null.");
        }
        model.getFormRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("formRef"));
        model.setKey(entity.getKey());
        model.setUpdateDate((java.util.Date) entity.getProperty("updateDate"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("createDate", m.getCreateDate());
        if (m.getFormRef() == null) {
            throw new NullPointerException("The property(formRef) must not be null.");
        }
        entity.setProperty("formRef", m.getFormRef().getKey());
        entity.setProperty("updateDate", m.getUpdateDate());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        if (m.getFormRef() == null) {
            throw new NullPointerException("The property(formRef) must not be null.");
        }
        m.getFormRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        m.setCreateDate(slim3_createDateAttributeListener.prePut(m.getCreateDate()));
        m.setUpdateDate(slim3_updateDateAttributeListener.prePut(m.getUpdateDate()));
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        com.plucial.mulcms.model.widgets.form.FormAction m = (com.plucial.mulcms.model.widgets.form.FormAction) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCreateDate() != null){
            writer.setNextPropertyName("createDate");
            encoder0.encode(writer, m.getCreateDate());
        }
        if(m.getFormRef() != null && m.getFormRef().getKey() != null){
            writer.setNextPropertyName("formRef");
            encoder0.encode(writer, m.getFormRef(), maxDepth, currentDepth);
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getUpdateDate() != null){
            writer.setNextPropertyName("updateDate");
            encoder0.encode(writer, m.getUpdateDate());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected com.plucial.mulcms.model.widgets.form.FormAction jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        com.plucial.mulcms.model.widgets.form.FormAction m = new com.plucial.mulcms.model.widgets.form.FormAction();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("createDate");
        m.setCreateDate(decoder0.decode(reader, m.getCreateDate()));
        reader = rootReader.newObjectReader("formRef");
        decoder0.decode(reader, m.getFormRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("updateDate");
        m.setUpdateDate(decoder0.decode(reader, m.getUpdateDate()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}