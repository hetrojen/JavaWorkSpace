package com.company.rating.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class RatingFormulaParser {
	private static final char DIVIDE_OP = 'D';
	private static final char SUM_OP = 'A';
	private static final char MULTIPLY_OP = 'M';
	public  static final char CODE_PREFIX = '$';
	private static  final char SUBTRACTION_OP='S';
	private HashMap<String, BigDecimal> codeMap;

	public RatingFormulaParser(HashMap<String, BigDecimal> codeMap) {
		// TODO Auto-generated constructor stub
		this.codeMap = codeMap;
	}

	private String parseCodes(String formula) {
	     String mainFormula=formula;  	
	     
	       int begin=0, end=0; 
	       char  sufix=' ';
		   for(int i=0;  i<formula.length();i++){
			   if(formula.charAt(i)==CODE_PREFIX){
				begin=i;   
			   }
			   if(begin!=0 && (formula.charAt(i)==',' || formula.charAt(i)==']')){
				end=i;   
				sufix=formula.charAt(i);
			   }
			   if(begin!=0  && end!=0){
				   String code=formula.substring(begin, end);
				   BigDecimal val=codeMap.get(code);
				   mainFormula=mainFormula.replace(code+sufix, val.toString()+sufix);
				   begin=0;
				   end=0;
			   }
		   }
		
		
		System.out.println(mainFormula);
		return mainFormula;
	}

	private BigDecimal evalBasicFormula(String formula) {
		char op = formula.charAt(0);
		BigDecimal result=new BigDecimal(0);
		String basicformul = formula.replace(op + "[", "");
		ArrayList<BigDecimal> values = new ArrayList<BigDecimal>();
		int begin = 0, end = -1;
		for (int i = 0; i < basicformul.length(); i++) {
			if (basicformul.charAt(i) == ',' || basicformul.charAt(i) == ']') {
				end = i;
				String val = basicformul.substring(begin, end);
				values.add(new BigDecimal(val));
				begin = i + 1;

			}
		}
		 if(values.size()==0){
			 return result;
		 }
		 if(values.size()==1){
			 return values.get(0);
		 }
		switch (op) {
		case SUM_OP:
                for(BigDecimal val:values){
                	result=result.add(val);
                }
			break;
		case SUBTRACTION_OP:
			   result= values.get(0);
               for(int i=1; i<values.size();i++){
             	  result=result.subtract(values.get(i));
               }
			break;
		case MULTIPLY_OP:
                  result=new BigDecimal(1);
             	 for(BigDecimal val:values){
                  	result=result.multiply(val);
                  }
			break;
		case DIVIDE_OP:
			      try{
			    	   result= values.get(0).divide(values.get(1), 3, BigDecimal.ROUND_HALF_EVEN);
			      }catch (ArithmeticException e) {
					// TODO: handle exception
			    	 result=new BigDecimal(0);
				}
                 
                  for(int i=2; i<values.size();i++){
                	  try{
                		  result=result.divide(values.get(i),3, BigDecimal.ROUND_HALF_EVEN);
                	  }catch (ArithmeticException e) {
						// TODO: handle exception
                		  result=new BigDecimal(0);
					}
                	  
                  }
			break;
		default:
			break;
		}


		return result;
	}
    private  boolean equaltoOp(char cr){
    	if(cr==MULTIPLY_OP || cr==DIVIDE_OP || cr==SUM_OP || cr==SUBTRACTION_OP){
    		return true;
    	}
    	return false;
    }
    private  boolean containsOp(String formula){
    	for(int i=0;i<formula.length();i++){
            if(equaltoOp(formula.charAt(i))){
            	return true;
            }
    	}
    	
    	return false;
    }

	private String findFirstEvaluable(String formula) {
		
	     String evaluable=null;  	
	       int begin=-1, end=-1, other=-1; 
	       char  sufix=' ';
		   for(int i=0;  i<formula.length();i++){
			   if(equaltoOp(formula.charAt(i)) && begin==-1){
					begin=i;  
					continue;
				   }
			   if(equaltoOp(formula.charAt(i)) && begin!=-1){
				   other=i;   
				  
				   }
			   if(formula.charAt(i)==']'){
				   if(begin!=-1 && other==-1){
					  end=i;
					  return formula.substring(begin,end+1);
				   
				   }else if(begin!=-1 && other!=-1){
					   end=i;
					   return formula.substring(other,end+1);
				   }
			   }
	
		   }
		if(evaluable==null){
			return formula;
		}
		return null;
	}

	public BigDecimal eval(String formula) {
		if(formula==null){
			return null;
		}
		String parsedFormula=parseCodes(formula);
	     String basicformul;
		while(true){
			  basicformul=findFirstEvaluable(parsedFormula);
			  BigDecimal r=evalBasicFormula(basicformul);
			  parsedFormula=parsedFormula.replace(basicformul, r.toString());
			  if(!(containsOp(parsedFormula))){
				  return r;
			  }
		}
		
		
	}
     public static void main(String[] args) {
		HashMap<String, BigDecimal> map=new HashMap<String, BigDecimal>();
		map.put("$201", new BigDecimal(1));
		map.put("$202", new BigDecimal(2));
		map.put("$203", new BigDecimal(3));
		map.put("$20", new BigDecimal(4));
		map.put("$205", new BigDecimal(5));
		String   formula="A[$201,S[$202,D[$20,$205]],$203]";
		RatingFormulaParser formulaParser=new RatingFormulaParser(map);
		System.out.println(formulaParser.eval(formula));
	}
	
}
