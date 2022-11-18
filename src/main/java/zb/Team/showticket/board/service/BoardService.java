package zb.Team.showticket.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.board.domain.entity.Board;
import zb.Team.showticket.board.domain.entity.Post;
import zb.Team.showticket.board.domain.entity.ShowPost;
import zb.Team.showticket.board.domain.model.PostDto;
import zb.Team.showticket.board.domain.model.ShowPostDto;
import zb.Team.showticket.board.domain.repository.BoardRepository;
import zb.Team.showticket.board.domain.repository.PostRepository;
import zb.Team.showticket.board.domain.repository.ShowPostRepository;
import zb.Team.showticket.user.domain.entity.Users;
import zb.Team.showticket.user.domain.repository.UserRepository;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ShowPostRepository showPostRepository;

    // 게시판 이름으로 게시판 정보 조회
    public Board findBoard(String boardName) {
        return Optional.ofNullable(boardRepository.findByName(boardName))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));
    }

    // 게시판 이름으로 게시글 리스트 조회
    public List<Post> findPosts(String boardName) {
        if (boardName.equals("공연")){
            throw new CustomException(ErrorCode.NOT_FOUND_BOARD);
        }
        return postRepository.findByBoard(findBoard(boardName));
    }

    // 공연 게시글 리스트 조회
    public List<ShowPost> findShowPosts(String boardName){
        if (!(boardName.equals("공연"))){
            throw new CustomException(ErrorCode.READ_ONLY_SHOW_BOARD);
        }
        return showPostRepository.findByBoard(findBoard(boardName));
    }


    // 게시글ID로 게시물 단건 상세 조회
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEWS));
    }

    // 게시글ID로 공연 게시물 단건 상세 조회
    public ShowPost getShowPost(Long postId) {
        return showPostRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REVIEWS));
    }

    // 게시글 등록
    public Post writePost(Long id, String email, String boardName, PostDto postDto) {
        Board board = findBoard(boardName);
        String btype = board.getBoardType();
        if ((boardName.equals("공연"))){
            throw new CustomException(ErrorCode.NOT_FOUND_BOARD);
        }

        Post post = new Post(userRepository.findByIdAndEmail(id, email).
                orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER))
                , board, postDto.getTitle(), postDto.getContent());

        // user 타입과 게시판 타입이 일치하지 않을경우 예외발생
        if (!post.getUser_type().contains(btype)) {
            throw new CustomException(ErrorCode.NOT_MACTH_USERTYPE);
        }
        return postRepository.save(post);
    }

    //공연 게시글 등록
    public ShowPost writeShowPost(Long id, String email, String boardName,ShowPostDto showPostDto) {
        Board board = findBoard(boardName);
        if (!(boardName.equals("공연"))){
            throw new CustomException(ErrorCode.ADD_ONLY_SHOW_BOARD);
        }

        String btype = board.getBoardType();

        ShowPost post = new ShowPost(userRepository.findByIdAndEmail(id, email).
                orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER))
                , board, showPostDto);

        // user 타입과 게시판 타입이 일치하지 않을경우 예외발생
        if (!post.getUser_type().contains(btype)) {
            throw new CustomException(ErrorCode.NOT_MACTH_USERTYPE);
        }
        return showPostRepository.save(post);
    }


    // 게시글 수정
    public Post updatePost(long postId, Long id, PostDto postDto) {
        Post post = getPost(postId);
        Users user = post.getUser();
        if (!id.equals(user.getId())) {
            throw new CustomException(ErrorCode.WRONG_MATCH_USER);
        }
        post.setUpdate(postDto.getTitle(), postDto.getContent());
        return postRepository.save(post);
    }

    // 공연 게시글 수정
    public ShowPost updateShowPost(long postId, Long id, ShowPostDto showPostDto) {
        ShowPost showPost = getShowPost(postId);
        Users user = showPost.getUser();
        if (!id.equals(user.getId())) {
            throw new CustomException(ErrorCode.WRONG_MATCH_USER);
        }
        showPost.setUpdate(showPostDto);
        return showPostRepository.save(showPost);
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

    // 공연 게시글 삭제
    public boolean deleteShowPost(long postId, Long id) {
        ShowPost showPost = getShowPost(postId);
        Users user = showPost.getUser();
        if (!id.equals(user.getId()))
            throw new CustomException(ErrorCode.WRONG_MATCH_USER);
        else {
            showPostRepository.delete(showPost);
        }
        return true;
    }
}

