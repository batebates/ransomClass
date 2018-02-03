package controller;

import module.Vector;

public interface IVisitor {
    void visit(IVisitable o);
    void visit(RecupData o);
    void visit(Vector o);
}
