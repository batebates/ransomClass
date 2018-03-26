package controller;

import module.Vector;

public class OptimVisitor implements IVisitor{
    @Override
    public void visit(IVisitable o) {

    }

    @Override
    public void visit(RecupData o) {
        System.out.println("Nombre d'execution RecupData Eachline : " + o.getEachLines());
        System.out.println("Nombre d'execution RecupData EachlineSplit : " + o.getEachLinesSplit());
    }

    @Override
    public void visit(Vector o) {
        System.out.println("Nombre d'execution Vector cpt : " + o.getCpt());
    }
}
