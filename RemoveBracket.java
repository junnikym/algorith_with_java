import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 백준 - 2800번: 괄호 제거
 * https://www.acmicpc.net/problem/2800
 */
public class RemoveBracket {

	private static List<Integer[]> bracketPairs = new ArrayList<>();
	private static String wholeStr = "";

	private static Set<String> result = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wholeStr = br.readLine();
		char[] inputArr = wholeStr.toCharArray();

		final char openBarcket = '(';
		final char closeBarcket = ')';

		// -- Stacking
		Stack<Integer> openBarcketIdxs = new Stack<>();

		for(Integer i = 0; i < inputArr.length; i++) {

			if(inputArr[i] == openBarcket)
				openBarcketIdxs.push(i);

			if(inputArr[i] == closeBarcket)
				bracketPairs.add(new Integer[] {openBarcketIdxs.pop(), i});

		}

		for(int size=1; size <= bracketPairs.size(); size++) {
			boolean[] visited = new boolean[bracketPairs.size()];
			combination(visited, 0, bracketPairs.size(), size);
		}

		final List<String> sortedResult = result.stream().collect(Collectors.toList());
		sortedResult.sort(String::compareTo);
		sortedResult.forEach(System.out::println);

	}

	static void combination(
			boolean[] visited,
			int start,
			int n,
			int r
	) {
		if(r == 0) {
			remove(visited, n);
			return;
		}

		for(int i=start; i<n; i++) {
			visited[i] = true;
			combination(visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	static void remove(boolean[] visited, int n) {
		StringBuilder sb = new StringBuilder(wholeStr);
		List<Integer> removeTarget = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				final Integer[] idxPair = bracketPairs.get(i);
				removeTarget.add(idxPair[0]);
				removeTarget.add(idxPair[1]);
			}
		}

		removeTarget.sort((lhs, rhs)->(rhs-lhs));
		for(Integer i : removeTarget) {
			sb.deleteCharAt(i);
		}

		result.add(sb.toString());
	}

}