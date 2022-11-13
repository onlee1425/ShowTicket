package zb.Team.showticket.board.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.UserVo;
import zb.Team.showticket.board.domain.entity.Board;
import zb.Team.showticket.board.domain.entity.Post;
import zb.Team.showticket.board.domain.model.PostDto;
import zb.Team.showticket.board.responsehandler.CommonResult;
import zb.Team.showticket.board.responsehandler.ListResult;
import zb.Team.showticket.board.responsehandler.ResponseService;
import zb.Team.showticket.board.responsehandler.SingleResult;
import zb.Team.showticket.board.service.BoardService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;
    private final JwtAuthProvider provider;

    @ApiOperation(value = "게시판 정보 조회", notes = "게시판의 정보를 조회한다.")
    @GetMapping(value = "/{boardName}")
    public SingleResult<Board> boardInfo(@PathVariable String boardName) {
        return responseService.getSingleResult(boardService.findBoard(boardName));
    }

    @ApiOperation(value = "게시판 글 리스트", notes = "게시판의 게시글 모든 게시글을 조회한다.")
    @GetMapping(value = "/{boardName}/posts")
    public ListResult<Post> posts(@PathVariable String boardName) {
        return responseService.getListResult(boardService.findPosts(boardName));
    }

    @ApiOperation(value = "게시판 글 상세", notes = "게시판의 단건 게시글의 상세정보를 조회한다.")
    @GetMapping(value = "/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId) {
        return responseService.getSingleResult(boardService.getPost(postId));
    }

    @ApiOperation(value = "게시판 글 작성", notes = "게시판에 글을 작성한다.")
    @PostMapping(value = "/{boardName}")
    public SingleResult<Post> post(@RequestHeader(name = "AUTH-TOKEN") String token, @PathVariable String boardName, @Valid @ModelAttribute PostDto post) {
        UserVo vo = provider.getUserVo(token);
        Long id = vo.getId();
        String email = vo.getEmail();
        return responseService.getSingleResult(boardService.writePost(id,email, boardName, post));

        //id를 검증해서 유저타입<->보드타입 비교 후 보드서비스로 넘겨주기
    }

    @ApiOperation(value = "게시판 글 수정", notes = "게시판의 단건 게시글을 수정한다.")
    @PutMapping(value = "/post/{postId}")
    public SingleResult<Post> post(@RequestHeader(name = "AUTH-TOKEN") String token, @PathVariable long postId, @Valid @ModelAttribute PostDto post) {
        UserVo vo = provider.getUserVo(token);
        Long id = vo.getId();

        return responseService.getSingleResult(boardService.updatePost(postId, id, post));
    }

    @ApiOperation(value = "게시판 글 삭제", notes = "게시판의 단건 게시글을 삭제한다.")
    @DeleteMapping(value = "/post/{postId}")
    public CommonResult deletePost(@RequestHeader(name = "AUTH-TOKEN") String token, @PathVariable long postId) {
        UserVo vo = provider.getUserVo(token);
        Long id = vo.getId();
        boardService.deletePost(postId, id);
        return responseService.getSuccessResult();
    }
}
