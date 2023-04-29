package com.bytedusk.kuafu.bcel;

class TestTemplate {
    private String packageName;
    private String importStrList;
    private String classHead;
    private String attributes;
    private String methodHead;
    private String methodContent;
    private String methodTail = "}\n";
    private String classTail = "}\n";

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getImportStrList() {
        return importStrList;
    }

    public void setImportStrList(String importStrList) {
        this.importStrList = importStrList;
    }

    public String getClassHead() {
        return classHead;
    }

    public void setClassHead(String classHead) {
        this.classHead = classHead;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getMethodHead() {
        return methodHead;
    }

    public void setMethodHead(String methodHead) {
        this.methodHead = methodHead;
    }

    public String getMethodContent() {
        return methodContent;
    }

    public void setMethodContent(String methodContent) {
        this.methodContent = methodContent;
    }

    public String getMethodTail() {
        return methodTail;
    }

    public void setMethodTail(String methodTail) {
        this.methodTail = methodTail;
    }

    public String getClassTail() {
        return classTail;
    }

    public void setClassTail(String classTail) {
        this.classTail = classTail;
    }

    public String toString(){
        return this.packageName + this.importStrList
                + this.classHead + this.attributes
                + this.methodHead + this.methodContent
                + this.methodTail + this.classTail;
    }
}
