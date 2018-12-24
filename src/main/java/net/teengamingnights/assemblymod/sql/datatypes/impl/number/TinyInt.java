package net.teengamingnights.assemblymod.sql.datatypes.impl.number;

import net.teengamingnights.assemblymod.sql.datatypes.Datatype;
import org.apache.commons.lang.math.IntRange;

public class TinyInt extends Datatype<Byte> {

    private int digits;

    public TinyInt(int digits) {
        this.digits = digits;
    }

    @Override
    public String getName() {
        return "TINYINT";
    }

    @Override
    public boolean takesParameters() {
        return true;
    }

    @Override
    public Class<Byte> getJavaType() {
        return byte.class;
    }

    @Override
    public boolean matchesConstraints(Byte toTest) {
        return new IntRange(-128, 127).containsInteger(toTest.intValue()) &&
                toTest.toString().replace("-", "").length() <= digits;
    }
}
