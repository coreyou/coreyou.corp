package setExtendToMap;

public class SetToMapDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SetToMap<String, Integer> scores = new SetToMap<String, Integer>();
		scores.put("語文", 89);
		scores.put("數學", 90);
		scores.put("英文", 80);
		System.out.println(scores);
		System.out.println(scores.size());
		
		scores.removeEntry("數學");
		System.out.println(scores);
		System.out.println(scores.size());
		
		System.out.println("語文成績:" + scores.get("語文"));
		System.out.println("是否包含\"英文\"key: " + scores.containsKey("英文"));
		System.out.println("是否包含 82 value: " + scores.containsValue(82));
		scores.clear();
		System.out.println(scores);
	}

}
