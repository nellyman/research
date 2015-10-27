package com.nbh.tutorials.patterns.Visitor;


public class VisitableString implements Visitable
{
    private final String value;
    public VisitableString(final String string) {
        this.value = string;
    }
    @Override
    public void accept(final Visitor visitor) {
        visitor.visitString(this.value);
    }
}
