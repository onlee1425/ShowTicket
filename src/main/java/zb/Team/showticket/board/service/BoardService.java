package zb.Team.showticket.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.board.domain.entity.Board;
import zb.Team.showticket.board.domain.entity.Post;
import zb.Team.showticket.board.domain.model.PostDto;
import zb.Team.showticket.board.domain.repository.BoardRepository;
import zb.Team.showticket.board.domain.repository.PostRepository;
import zb.Team.showticket.user.domain.entity.Users;
import zb.Team.showticket.user.domain.repository.UserRepository;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;
import zb.Team.showticket.user.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 게시판 이름으로 게시판 정보 조회
    public Board findBoard(String boardName) {
        return Optional.ofNullable(boardRepository.findByName(boardName))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));

    }

    // 게시판 이름으로 게시글 리스트 조회
    public List<Post> findPosts(String boardName) {
        return postRepository.findByBoard(findBoard(boardName));
    }


    // 게시글ID로 게시물 단건 상세 조회
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEWS));
    }

    // 게시글 등록
    public Post writePost(Long id,String email, String boardName, PostDto postDto) {
        Board board = findBoard(boardName);
        String btype = board.getBoardType();

        Post post = new Post(userRepository.findByIdAndEmail(id,email).
                orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER))
                , board, postDto.getTitle(), postDto.getContent());

        // user 타입과 게시판 타입이 일치하지 않을경우 예외발생
        if (!post.getUser_type().contains(btype)){
            throw new CustomException(ErrorCode.NOT_MACTH_USERTYPE);
        }
        return postRepository.save(post);
    }

    // 게시글 수정
    public Post updatePost(long postId, Long id, PostDto postDto) {
        Post post = getPost(postId);
        Users user = post.getUser();
        if (!id.equals(user.getId())){
            throw new CustomException(ErrorCode.WRONG_MATCH_USER);
        }
        post.setUpdate(postDto.getTitle(), postDto.getContent());
        return postRepository.save(post);
    }

    // 게시글 삭제
    public boolean deletePost(long postId, Long id) {
        Post post = getPost(postId);
        Users user = post.getUser();
        if (!id.equals(user.getId()))
            throw new CustomException(ErrorCode.WRONG_MATCH_USER);
        else {
            postRepository.delete(post);
        }
        return true;
    }
}

