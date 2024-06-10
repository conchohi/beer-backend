package com.zipbeer.beerbackend.service;

import com.zipbeer.beerbackend.dto.BoardDto;
import com.zipbeer.beerbackend.entity.BoardEntity;
import com.zipbeer.beerbackend.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //전체조회
    public List<BoardDto> getAllBoards() {
        List<BoardEntity> boards = boardRepository.findAll();

        List<BoardDto> boardDtos= new ArrayList<>();

        for (BoardEntity board : boards) {
            BoardDto boardDto = entityToDto(board);
            boardDtos.add(boardDto);
        }

        return boardDtos;
    }

    //조회
    public BoardDto getBoardById(Long id) {
        BoardEntity board = boardRepository.findById(id).get();

        return entityToDto(board);
    }

    //등록
    public BoardDto registerBoard(BoardDto board) {
        BoardEntity boardEntity = dtoToEntity(board);
        BoardEntity saveBoard = boardRepository.save(boardEntity);

        return entityToDto(saveBoard);
    }

    //수정
    public BoardDto updateBoard(Long id, BoardEntity board) {
        BoardEntity getBoard = boardRepository.findById(id).get();

        //제목, 내용, 수정일자
        getBoard.setTitle(board.getTitle());
        getBoard.setContent(board.getContent());
        getBoard.setModifyDate(LocalDate.now());

        BoardEntity updateBoard = boardRepository.save(getBoard);

        return entityToDto(updateBoard);
    }

    //삭제
    public void deleteBoard(Long id) {
        BoardEntity board = boardRepository.findById(id).get();
        boardRepository.delete(board);
    }


    //Entity를 Dto로 변환하는 메소드
    private BoardDto entityToDto(BoardEntity boardEntity) {
        BoardDto boardDto = BoardDto.builder()
                .boardNo(boardEntity.getBoardNo())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .regDate(boardEntity.getRegDate())
                .modifyDate(boardEntity.getModifyDate())
                .count(boardEntity.getCount())
                .build();

        return boardDto;
    }

    // Dto를 Entity로 변환하는 메소드
    private BoardEntity dtoToEntity(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .regDate(boardDto.getRegDate())
                .modifyDate(boardDto.getModifyDate())
                .build();

        return boardEntity;
    }

}