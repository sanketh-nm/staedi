package io.xlate.edi.internal.schema.implementation;

import java.util.Set;

import io.xlate.edi.schema.EDIReference;
import io.xlate.edi.schema.EDISimpleType;
import io.xlate.edi.schema.implementation.ElementImplementation;

public class ElementImpl extends BaseImpl<EDISimpleType> implements ElementImplementation, Positioned {

    private static final String TOSTRING_FORMAT = "typeId: %s, minOccurs: %d, maxOccurs: %d, position: %d, values: %s, standard: { %s }";
    private final int position;
    private final Set<String> values;

    public ElementImpl(int minOccurs,
            int maxOccurs,
            String typeId,
            int position,
            Set<String> values,
            String title,
            String description) {
        super(title, description);
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
        this.typeId = typeId;
        this.position = position;
        this.values = values;
    }

    public ElementImpl(EDIReference standardReference, int position) {
        super(null, null);
        this.setStandardReference(standardReference);
        this.typeId = standard.getId();
        this.position = position;
        this.values = standard.getValueSet();
    }

    @Override
    public String toString() {
        return String.format(TOSTRING_FORMAT, typeId, minOccurs, maxOccurs, position, values, standard);
    }

    @Override
    public Set<String> getValueSet() {
        return values;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Base getBase() {
        return standard.getBase();
    }

    @Override
    public int getNumber() {
        return standard.getNumber();
    }

    @Override
    public long getMinLength() {
        return standard.getMinLength();
    }

    @Override
    public long getMaxLength() {
        return standard.getMaxLength();
    }
}
