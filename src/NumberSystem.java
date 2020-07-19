public class NumberSystem {

    public final String numSystem; 

    public NumberSystem(String expSample) {
        final String template = "IVX";
        String s = "";
        boolean b = false;
        //проверяем выражение на то, что это число
        for (int i = 0; i < expSample.length(); i++) {
            if (!Character.isDigit(expSample.charAt(i))) {
                b = true;
                break;
            }
        }

        if (!b)
            s = "A";
        else { //если не число, пытаемся проверить на римскую СС
            b = false;
            for (int i = 0; i < expSample.length(); i++) {
                if (!template.toLowerCase().contains(expSample.substring(i, i+1).toLowerCase())) {
                    b = true;
                    break;
                }
            }    
        
            if (!b)
                s = "R";
        }

        numSystem = s;
    }

}