package join.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import join.VO.joinVO;

public class joinDAO {
	Scanner sc = new Scanner(System.in);

	joinVO jvo;
	boolean alpha = false;
	boolean pwNum = false;
	boolean idSize = false;
	boolean pwSize = false;
	File file = new File("password.txt");
	FileReader fr = null;
	FileWriter fw = null;
	int i;

	public void joinConsol() throws IOException, NoSuchAlgorithmException {
		String sw = "Y";
		String cont = "";
		while (sw.equals("Y")) {

			registUser();
			System.out.print("Continue?[Y/N] : ");
			cont = sc.next();
			sw = cont.toUpperCase();

		}
		System.out.println("Good bye.");

	}

	public void registUser() throws IOException, NoSuchAlgorithmException {
		ArrayList<joinVO> arrJoin = new ArrayList<joinVO>();
		i = 0; // sample.txt 파일을 File 객체로 가져온다.

		String id = "";
		String pw = "";
		jvo = new joinVO();
		System.out.println("Regist new ID");

		while (alpha == false || idSize == false) {
			System.out.print("ID: ");
			id = sc.next();
			chkInputId(id);

		}
		while (pwSize == false || pwNum == false) {

			System.out.print("PASS: ");
			pw = sc.next();
			chkInputPw(pw);
		}

		jvo.setId(id);
		jvo.setPw(SHA_1.makeSHA_1(pw));
		arrJoin.add(jvo);

		String wLine = "";
		try { // InputStreamReader로 콘솔에서 입력한 문자를 가져오는 객체를 생성한다.

			// true 이므로, 기존의 sample.txt 파일 뒤에 이어서 출력한다.

			fw = new FileWriter(file, true);

			for (int k = 0; k < arrJoin.size(); k++) {
				wLine = arrJoin.get(k).getId() + ", " + arrJoin.get(k).getPw();
				fw.write(wLine + "\n");
				System.out.println();
			} // 입력받은 버퍼를 파일에 내보낸다.

			fw.flush(); // sample.txt 파일을 읽을 FileReader 객체를 생성한다.

		} catch (IOException e) {
			e.printStackTrace();
		} finally { // InputStreamReader, FileReader, FileWriter를 닫아준다.

			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
				}
		}

		System.out.println("New account is created.");
		alpha = false;
		pwNum = false;
		idSize = false;
		pwSize = false;

	}

	public void chkInputId(String textInput) {

		String pattern = "^[a-zA-Z]*$";
		boolean alphaChk = Pattern.matches(pattern, textInput);
		if (alphaChk == true) {
			alpha = true;
			if (textInput.length() < 4) {
				System.out.println("ERROR. Minimum length : 4");

			} else if (textInput.length() > 8) {
				System.out.println("ERROR. Maximum length : 8");

			} else {
				idSize = true;
			}

		} else {
			System.out.println("ERROR. Alphabet Only");
		}
	}

	public void chkInputPw(String textInput) {

		char chrInput;

		Pattern p = Pattern.compile("(^[0-9]*$)");

		int onlyNum;
		String inputVal;

		Matcher m = p.matcher(textInput);

		if (m.find()) {
			onlyNum = Integer.parseInt(textInput);
			System.out.println(onlyNum);
			pwNum = true;
			if (textInput.length() < 6) {
				System.out.println("ERROR. Minimum length : 6");
			} else if (textInput.length() > 8) {
				System.out.println("ERROR. Maximum length : 8");
			} else {
				pwSize = true;
			}
		} else {
			System.out.println("ERROR. Number Only");
		}

	}

	public void login() throws NoSuchAlgorithmException {
		String id = "";
		String pw = "";
		boolean matchId = false;
		boolean matchPw = false;
		while (matchId == false) {
			int j = 0;

			System.out.print("INPUT ID: ");
			id = sc.next();
			try {
				fr = new FileReader(file); // sample.txt 파일의 끝까지 읽으면서 콘솔에 출력한다.
				String row = Integer.toString(i);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				String[] splitedStr = null;
				String[] compareStr = null;

				while ((line = reader.readLine()) != null) {

					splitedStr = null;

					// 탭을 기준으로 잘라서 splitedStr 에 넣는다
					splitedStr = line.split(",");
					for (int i = 0; i < splitedStr.length; i++) {

						// 양쪽의 공백을 제거하고 다시 입력한다
						splitedStr[i] = splitedStr[i].trim();

						if (i == 0) {

							if (id.equals(splitedStr[i])) {

								++j;
							}

						}
						// splitedStr 을 List<Class명>에 입력하는 등 이용가능하다
					}

				}
				if (j >= 1) {

					matchId = true;
				} else {
					System.out.println("ERROR. Invalid ID :" + id);
				}
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			} finally { // InputStreamReader, FileReader, FileWriter를 닫아준다.
				if (fr != null)
					try {
						fr.close();
					} catch (IOException e) {
					}

			}
		}

		while (matchPw == false) {
			int j = 0;

			System.out.print("PASS: ");
			pw = sc.next();

			try {
				fr = new FileReader(file); // sample.txt 파일의 끝까지 읽으면서 콘솔에 출력한다.
				String row = Integer.toString(i);
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				String[] splitedStr = null;
				String[] compareStr = null;

				while ((line = reader.readLine()) != null) {

					splitedStr = null;

					// 탭을 기준으로 잘라서 splitedStr 에 넣는다
					splitedStr = line.split(",");
					for (int i = 0; i < splitedStr.length; i++) {

						// 양쪽의 공백을 제거하고 다시 입력한다
						splitedStr[i] = splitedStr[i].trim();

						if (i == 1) {

							if (SHA_1.makeSHA_1(pw).equals(splitedStr[i])) {

								++j;
							}

						}
						// splitedStr 을 List<Class명>에 입력하는 등 이용가능하다
					}

				}
				if (j >= 1) {
					System.out.println("Success");
					matchPw = true;
					System.out.println("Goodbye");
				} else {
					System.out.println("ERROR. Invalid Password!");
				}
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			} finally { // InputStreamReader, FileReader, FileWriter를 닫아준다.
				if (fr != null)
					try {
						fr.close();
					} catch (IOException e) {
					}

			}
		}

	}

}
