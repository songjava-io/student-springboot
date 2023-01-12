package kr.songjava.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.songjava.web.domain.Board;

/**
 * 게시판 Dao
 * @author youtube
 *
 */
public interface BoardMapper {
	
	/**
	 * 게시물 목록 조회 리턴
	 * @return
	 * @throws SQLException
	 */
	public List<Board> selectBoardList(Map<String, Object> paramMap) 
			throws SQLException;

	/**
	 * 게시물 조회
	 * @param boardSeq
	 * @return
	 * @throws SQLException
	 */
	public Board selectBoard(String boardSeq) throws SQLException;
	/**
	 * 게시물 등록
	 * @param board
	 * @throws SQLException
	 */
	public void insertBoard(Board board) throws SQLException;
	
	/**
	 * 게시물 업데이트
	 * @param board
	 * @throws SQLException
	 */
	public void updateBoard(Board board) throws SQLException;
	
	/**
	 * 게시물 삭제
	 * @param board
	 * @throws SQLException
	 */
	public void deleteBoard(int boardSeq) throws SQLException;
	
}
