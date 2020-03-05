package bmicalculator.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.function.DoubleToLongFunction;

@Controller
public class BmiController {


    @GetMapping("/")
    public String getController() {
        return "home";
    }

    @RequestMapping(value="/wynik", method = {
            RequestMethod.GET, RequestMethod.POST
    })
    public String results(@RequestParam Double weight,
                          @RequestParam Double height,
                           @RequestParam(required = false) Double bmi,  //(required=false) - nie jest wymagany
                          @RequestParam String sex,
                          ModelMap map) {

        if (weight != null && height != null && height != 0) {

            //Obliczanie współczynnika BMI dla wartości podanej w cm i zamiana na metry
            Double bmiCalculation = (weight / (Math.pow(height, 2) / 10000));

            //zaokrąglenie BMI do dwóch miejsc po przecinku
            Double bmiRound=(double)Math.round(bmiCalculation*100)/100;

            map.put("bmi", bmi=bmiRound);
        }

        if (sex.equals("female")) {
            map.put("sex", "I dla kobiet, ");
        } else {
            map.put("sex", "I dla mężczyzn, ");
        }


        if (bmi < 16 && sex.equals("female")) {
            map.put("przedzial", "Wygłodzenie");
            map.put("icons", "/i1.png");
        }else if (bmi < 16 && sex.equals("male")) {
            map.put("przedzial", "Wygłodzenie");
            map.put("icons", "/m1.jpg");


        } else if (bmi > 16 && bmi < 16.99 && sex.equals("female")) {
            map.put("przedzial", "Wychudzenie");
            map.put("icons", "/i1.png");
        } else if (bmi > 16 && bmi < 16.99 && sex.equals("male")) {
            map.put("przedzial", "Wychudzenie");
            map.put("icons", "/m1.jpg");

        } else if (bmi > 17 && bmi < 18.49 && sex.equals("female")) {
            map.put("przedzial", "Niedowagę");
            map.put("icons", "/i1.png");
        } else if (bmi > 17 && bmi < 18.49 && sex.equals("male")) {
            map.put("przedzial", "Niedowagę");
            map.put("icons", "/m1.jpg");

        } else if (bmi > 18.5 && bmi < 24.99 && sex.equals("female")) {
            map.put("przedzial", "Wartość prawidłową");
            map.put("icons", "/i2.png");
        } else if (bmi > 18.5 && bmi < 24.99 && sex.equals("male")) {
            map.put("przedzial", "Wartość prawidłową");
            map.put("icons", "/m2.jpg");

        } else if (bmi > 25 && bmi < 29.99 && sex.equals("female")) {
            map.put("przedzial", "Nadwagę");
            map.put("icons", "/i3.png");
        } else if (bmi > 25 && bmi < 29.99 && sex.equals("male")) {
            map.put("przedzial", "Nadwagę");
            map.put("icons", "/m3.jpg");

        } else if (bmi > 30 && bmi < 34.99 && sex.equals("female")) {
            map.put("przedzial", "I stopień otyłości");
            map.put("icons", "/i3.png");
        } else if (bmi > 30 && bmi < 34.99 && sex.equals("male")) {
            map.put("przedzial", "I stopień otyłości");
            map.put("icons", "/m3.jpg");

        } else if (bmi > 35 && bmi < 39.99 && sex.equals("female")) {
            map.put("przedzial", "II stopień otyłości");
            map.put("icons", "/i3.png");
        } else if (bmi > 35 && bmi < 39.99 && sex.equals("male")) {
            map.put("przedzial", "II stopień otyłości");
            map.put("icons", "/m3.jpg");

        } else if (bmi > 40 && sex.equals("female")){
            map.put("przedzial", "Otyłość skrajną");
            map.put("icons", "/i3.png");
        } else if (bmi > 40 && sex.equals("male")){
            map.put("przedzial", "Otyłość skrajną");
            map.put("icons", "/m3.jpg");

        }

        return "results";

    }

    @GetMapping("/ciekawostki")
    public String information() {
        return "information";
    }

}
