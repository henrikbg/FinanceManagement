//package FinanceManagement;

/*
    Depreciation.java
    Class that calculates depreciation in the application
*/

public class Depreciation {
    
    public static double calculateDepreciation(double initialAmount, double depreciationRate, int depreciationTimeAmount, String depreciationTime, String frequencyOfDepreciation) {
        
        // Declaring variables to be used
        double finalAmount;
        int timesToDepreciate = 0;
        double depreciationDecimal = 1 - (depreciationRate / 100);
        
        // Calculations to compute depreciation over time
        if(depreciationTime.equals("Day(s)")) {
            
            if(frequencyOfDepreciation.equals("Every Day")) {
                timesToDepreciate = depreciationTimeAmount;
            }
            if(frequencyOfDepreciation.equals("Every Week")) {
                while(depreciationTimeAmount >= 7) {
                    depreciationTimeAmount = depreciationTimeAmount - 7;
                    timesToDepreciate++;
                }
            }
            if(frequencyOfDepreciation.equals("Every Month")) {
                while(depreciationTimeAmount >= 30) {
                    depreciationTimeAmount = depreciationTimeAmount - 30;
                    timesToDepreciate++;
                }
            }
            if(frequencyOfDepreciation.equals("Every Year")) {
                while(depreciationTimeAmount >= 365) {
                    depreciationTimeAmount = depreciationTimeAmount - 365;
                    timesToDepreciate++;
                }
            }
        }
        if(depreciationTime.equals("Week(s)")) {
           
            if(frequencyOfDepreciation.equals("Every Day")) {
                timesToDepreciate = depreciationTimeAmount * 7;
            }
            if(frequencyOfDepreciation.equals("Every Week")) {
                timesToDepreciate = depreciationTimeAmount;
            }
            if(frequencyOfDepreciation.equals("Every Month")) {
                while(depreciationTimeAmount >= 4) {
                    depreciationTimeAmount = depreciationTimeAmount - 4;
                    timesToDepreciate++;
                }
            }
            if(frequencyOfDepreciation.equals("Every Year")) {
                while(depreciationTimeAmount >= 52) {
                    depreciationTimeAmount = depreciationTimeAmount - 52;
                    timesToDepreciate++;
                }
            }
        }
        if(depreciationTime.equals("Month(s)")) {
            
            if(frequencyOfDepreciation.equals("Every Day")) {
                timesToDepreciate = depreciationTimeAmount * 30;
            }
            if(frequencyOfDepreciation.equals("Every Week")) {
                timesToDepreciate = depreciationTimeAmount * 4;
            }
            if(frequencyOfDepreciation.equals("Every Month")) {
                timesToDepreciate = depreciationTimeAmount;
            }
            if(frequencyOfDepreciation.equals("Every Year")) {
                while(depreciationTimeAmount >= 12) {
                    depreciationTimeAmount = depreciationTimeAmount - 12;
                    timesToDepreciate++;
                }
            }
        }
        if(depreciationTime.equals("Year(s)")) {
            
            if(frequencyOfDepreciation.equals("Every Day")) {
                timesToDepreciate = depreciationTimeAmount * 365;
            }
            if(frequencyOfDepreciation.equals("Every Week")) {
                timesToDepreciate = depreciationTimeAmount * 52;
            }
            if(frequencyOfDepreciation.equals("Every Month")) {
                timesToDepreciate = depreciationTimeAmount * 12;
            }
            if(frequencyOfDepreciation.equals("Every Year")) {
                timesToDepreciate = depreciationTimeAmount;
            }  
        }
        
        // Calculating the final amount after depreciation
        finalAmount = initialAmount * (Math.pow(depreciationDecimal, timesToDepreciate));
        
        finalAmount = Math.round(finalAmount * 10d) / 10d;
        
        return finalAmount;
        
    }
    
}
