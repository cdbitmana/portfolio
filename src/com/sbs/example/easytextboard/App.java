package com.sbs.example.easytextboard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {

	// App 클래스의 기본 생성자
	public App() {
		inIt();
	}

	// 회원 객체 배열 생성
	ArrayList<Member> memberList = new ArrayList<Member>();
	boolean loginstatus = false;

	// 게시물 객체 배열 생성
	ArrayList<Article> list = new ArrayList<Article>();
	int lastArticleId = 0;

	// 날짜 생성 클래스
	Calendar time = Calendar.getInstance();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String format = format1.format(time.getTime());

	// add 메소드
	private void add(String title, String body) {
		lastArticleId += 1;
		list.add(new Article(lastArticleId, title, body, format));

	}

	// memberAdd 메소드
	private void memberAdd(String id, String password, String name) {

		memberList.add(new Member(id, password, name));

	}

	// getArticle 메소드
	private Article getArticle(int id) {
		int index = getIndexById(id);
		if (index == -1) {
			return null;
		} else {
			return list.get(index);
		}

	}

	// getIndexById 메소드
	private int getIndexById(int id) {
		int index = 0;
		for (Article article : list) {
			if (article.id == id) {
				return index;
			}
			index++;
		}
		return -1;

	}

	// printList 메소드 : 게시물 페이지(searchNum)에 따라 10개씩 출력
	private void printList(ArrayList<Article> list, int searchNum) {
		int itemsInAPage = 10;
		int start = list.size() - 1;
		start -= (searchNum - 1) * itemsInAPage;
		int end = start - (itemsInAPage - 1);
		if (end < 0) {
			end = 0;
		}

		for (int i = start; i >= end; i--) {
			System.out.printf("%d / %s\n", list.get(i).id, list.get(i).title);
		}
	}

	// memberLogin 메소드
	private boolean memberLogin(String id, String password) {
		int index = 0;
		for (Member member : memberList) {
			if (member.id.equals(id) && member.password.equals(password)) {
				System.out.printf("%s님 환영합니다.\n", memberList.get(index).name);
				return true;
			}
			index++;
		}
		return false;
	}

	// inIt 메소드
	private void inIt() {

		for (int i = 0; i < 32; i++) {
			add("title" + (i + 1), "body" + (i + 1));
		}
	}

	// run 메소드
	public void run() {

		Scanner scanner = new Scanner(System.in);

		// 명령어 받아내는 반복문 시작
		while (true) {

			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			// 게시물 등록
			if (command.equals("article add")) {

				System.out.println("== 게시물 등록 ==");
				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				add(title, body);

				System.out.printf("%d번 게시물이 등록되었습니다.\n", lastArticleId);

				// 게시물 리스트
			} else if (command.startsWith("article list")) {

				String[] page = command.split(" ");
				System.out.println("== 게시물 리스트 ==");

				if (page.length < 3) { // 페이지 없이 검색할 때
					System.out.println("번호 / 제목");
					for (int i = list.size() - 1; i >= list.size() - 10; i--) {
						if (i < 0) {
							break;
						}
						System.out.printf("%d / %s\n", list.get(i).id, list.get(i).title);

					}
					continue;
				}
				int listNum = 0;
				try {
					listNum = Integer.parseInt(page[2]);
				} catch (Exception e) {
					System.out.println("페이지는 숫자로 입력해야 합니다.");
					continue;
				}

				if (list.size() <= 10 * (listNum - 1) || listNum <= 0) {
					System.out.println("존재하지 않는 페이지입니다.");
					continue;
				}

				printList(list, listNum);

				// 게시물 상세
			} else if (command.startsWith("article detail ")) {
				if (command.equals("article detail ")) {
					System.out.println("존재하지 않는 명령어");
					continue;
				}
				System.out.println("== 게시물 상세 ==");
				int detailNum = 0;

				try {
					detailNum = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("게시물 번호는 숫자로 입력해야 합니다.");
					continue;
				}

				Article detailArticle = getArticle(detailNum);

				if (detailArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.printf("번호 : %d\n", detailArticle.id);
				System.out.printf("제목 : %s\n", detailArticle.title);
				System.out.printf("내용 : %s\n", detailArticle.body);
				System.out.printf("날짜 : %s\n", detailArticle.regDate);
			}
			// 프로그램 종료
			else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;

			}
			// 게시물 삭제
			else if (command.startsWith("article delete ")) {
				if (command.equals("article delete ")) {
					System.out.println("존재하지 않는 명령어");
					continue;
				}

				int deleteNum = 0;
				try {
					deleteNum = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("게시물 번호는 숫자로 입력해야 합니다.");
					continue;
				}
				int deleteIndex = getIndexById(deleteNum);
				if (deleteIndex == -1) {
					System.out.println("존재하지 않는 게시물은 삭제할 수 없습니다.");
					continue;
				}
				System.out.println("==게시물 삭제==");
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", deleteNum);

				list.remove(deleteIndex);

			} // 게시물 수정
			else if (command.startsWith("article modify ")) {

				if (command.equals("article modify")) {
					System.out.println("존재하지 않는 명령어");
					continue;
				}
				int modifyNum = 0;
				try {
					modifyNum = Integer.parseInt(command.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("게시물 번호는 숫자로 입력해야 합니다.");
					continue;
				}
				Article article = getArticle(modifyNum);

				if (article == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				String title;
				String body;
				System.out.printf("%d번 게시물 수정\n", article.id);
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("새 제목 : ");
				title = scanner.nextLine();
				System.out.printf("새 내용 : ");
				body = scanner.nextLine();

				article.title = title;
				article.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", modifyNum);

			} // 게시물 검색 (article search 키워드 페이지)
			else if (command.startsWith("article search ")) {
				if (command.equals("article search")) {
					System.out.println("존재하지 않는 명령어");
				}

				String[] keyWord = command.split(" ");

				ArrayList<Article> searchList = new ArrayList<Article>(); // 검색된 게시물만 담을 새 리스트(searchList) 생성

				int searchListIndex = 0;
				for (Article article : list) { // 키워드로 검색된 게시물만 searchList 배열에 대입
					if (article.title.contains(keyWord[2])) {
						searchList.add(searchListIndex, article);
						searchListIndex++;
					}
				}

				if (searchList.size() == 0) {
					System.out.println("검색 결과가 없습니다.");
					continue;
				}

				if (keyWord.length < 4) { // 페이지 없이 키워드로만 검색할 때 (1페이지가 출력)

					for (int i = searchList.size() - 1; i >= searchList.size() - 10; i--) {
						if (i < 0) {
							break;
						}
						System.out.printf("%d / %s\n", searchList.get(i).id, searchList.get(i).title);

					}
					continue;
				}

				int searchNum = 0;
				try {
					searchNum = Integer.parseInt(keyWord[3]);
				} catch (NumberFormatException e) {
					System.out.println("페이지는 숫자로 입력해야 합니다.");
					continue;
				}

				if (searchList.size() <= 10 * (searchNum - 1) || searchNum <= 0) {
					System.out.println("존재하지 않는 페이지입니다.");
					continue;
				}

				System.out.println("==검색 결과 리스트==");
				System.out.println("번호 / 제목");

				printList(searchList, searchNum);

			} // member join 명령어
			else if (command.equals("member join")) {

				String memberId;
				String password;
				String name;

				System.out.printf("로그인 아이디 : ");
				memberId = scanner.nextLine();
				System.out.printf("로그인 비밀번호 : ");
				password = scanner.nextLine();
				System.out.printf("이름 : ");
				name = scanner.nextLine();

				memberAdd(memberId, password, name);

				System.out.println("가입이 완료되었습니다.");

			} // member login 명령어
			else if (command.equals("member login")) {
				if (loginstatus) {
					System.out.println("이미 로그인 되었습니다.");
					continue;
				}
				String memberId;
				String password;

				System.out.printf("로그인 아이디 : ");
				memberId = scanner.nextLine();
				System.out.printf("로그인 비밀번호 : ");
				password = scanner.nextLine();

				loginstatus = memberLogin(memberId, password);

				if (!loginstatus) {
					System.out.println("아이디와 비밀 번호가 일치하는 회원이 없습니다.");
				}
			}
			// member logout 명령어
			else if (command.equals("member logout")) {
				if (loginstatus) {
					System.out.println("로그아웃 되었습니다.");
					loginstatus = false;
					continue;
				} else if (!loginstatus) {
					System.out.println("로그인 되지 않았습니다.");
				}
			}

			// 존재하지 않는 명령어
			else {
				System.out.println("존재하지 않는 명령어");
			}

		}
		scanner.close();
	} // run 메소드 끝
}
