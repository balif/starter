package VertxTest.tree.verticle;

import VertxTest.tree.model.ITreeElement;

import java.util.Map;

public class TreeContext {
    private final ITreeElement element;
    private final Map<String,String> params;

    public TreeContext(ITreeElement element, Map<String, String> params) {
        this.element = element;
        this.params = params;
    }

    public ITreeElement getElement() {
        return element;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
