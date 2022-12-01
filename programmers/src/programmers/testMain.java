package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class testMain {

	public static void main(String[] args) {
		//solution 1
		System.out.println("test");
		System.out.println(solution1("a234"));
		System.out.println(solution1("1234"));
		System.out.println("--------------------------------------1");
		char la= 'a';
		char lz ='z';
		char ua ='A';
		char uz ='z';
		char sp =' ';
		System.out.println((int)la);
		System.out.println((int)lz);
		System.out.println((int)ua);
		System.out.println((int)uz);
		System.out.println((int)sp);
		System.out.println("--------------------------------------2");
		//solution2	아스키코드
		System.out.println(solution2("AB",1)); //BC
		System.out.println(solution2("z",1)); //a
		System.out.println(solution2("a B z",4)); // e F d
		System.out.println("--------------------------------------3");
		//solution3
		int[] arr = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3} ,{4, 4, 1}, {1, 7, 3}};
		int [] result = solution3(arr,commands);
		for(int r : result) {
			
			System.out.println(r);
		}
		System.out.println("완주하지못한선수---------------------------");
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		System.out.println(solution4(participant,completion));
		
		String num ="asbx";
		System.out.println(num.length());
		
		
		
		
	}

	public static  boolean solution1(String s) {
		boolean answer =false;

		try {
			if(s.length() ==4 || s.length() == 6) {
				Integer.parseInt(s);
				answer = true;
			}
		}catch(Exception e) {
			answer = false;
		}
		return answer;
	}
	
	public static String solution2(String s, int n) {
        String answer = "";
        char[] chars = s.toCharArray();
        
        int tuningVal = 'z'-'a'+1; //26
        int stdVal =0;
        for(char c: chars) {
        	if(c != ' ') {//띄어쓰기 제외
        		
        		if(Character.isLowerCase(c)) {
        			stdVal = 'z';
        		}
        		if(Character.isUpperCase(c)) {
        			stdVal ='Z';
        		}
        		c = (char)(c+n);
        		
        		
        		if(c>stdVal) {
        			 c -= tuningVal;
        		}
        	}
        	answer += c;
        }
        return answer;
    }
	
	public static int[] solution3(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<commands.length; i++)
        {
        	pq.clear();
        	for(int j=commands[i][0]-1; j<commands[i][1]; j++)
        	{
        		pq.add(array[j]);
        	}
        	for(int k=0; k<commands[i][2]; k++) {
        		answer[i] = pq.poll();
        	}
        }
        return answer;
    }
	//완주하지 못한 선수
	    public static String solution4(String[] participant, String[] completion) {
	        String answer = "";
	        Map<String,Integer> map = new HashMap<>();
//	        for(int i=0; i<participant.length; i++) {
//	        	
//	        }
	        for(String s: participant) {
//	        	map.put("key", value);
//	        	map.get("key");
//	        	map.containsKey("key"); //key에 해당하는게 map에 있냐
	        	if(map.containsKey(s)) {
	        		//있는경우
	        		map.put(s, map.get(s)+1);//원래있던 카운트 값에 +1
	        	}else {
	        		//없는경우
	        		map.put(s, 1);
	        	}
	        }
	        for(String s : completion) {
	        	map.put(s, map.get(s)-1);//원래있던 카운트 값에 -1 완주자(= 0) 제외시키기
	        }
	        
	        for(String key : map.keySet()) {
	        	//key 값 : String 참가자 이름 -> get(key) Value값 1인 녀석 찾기 =>완주못한사람
	        	if(map.get(key) ==1) {
	        		answer = key;
	        		break;
	        	}
	        }
			return answer;
}
}
