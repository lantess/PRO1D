package PRO1;

import java.util.function.Function;

public class Field {
    private String name;
    private Function<Float, String> mapper;

    public Field(String name, Function<Float, String> mapper){
        this.name = name;
        this.mapper = mapper;
    }

    public String getName(){
        return name;
    }

    public String getValue(float value){
        return mapper.apply(value);
    }
}
