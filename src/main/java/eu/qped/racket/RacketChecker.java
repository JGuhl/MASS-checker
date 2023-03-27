package eu.qped.racket;

import eu.qped.framework.Checker;
import eu.qped.framework.qf.QfObject;
import eu.qped.racket.interpret.DrRacketInterpreter;

public class RacketChecker implements Checker {
    @Override
    public void check(QfObject qfObject) throws Exception {
        String answer = qfObject.getAnswer();

        String message = "EMPTY";

        try {
            //DrRacketInterpreter inter = new DrRacketInterpreter(answer);
            //message = inter.evaluateExpressions();

        } catch (Exception e) {
            //System.out.println(e);
            message = e.getMessage();
        }


        String[] feedback = new String[2];

        feedback[0] = "# Hallo Welt";
        feedback[1] = answer;

        qfObject.setFeedback(feedback);
    }
}
