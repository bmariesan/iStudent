package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.NewsComments;
import ro.ubb.istudent.dto.GreetingDto;
import ro.ubb.istudent.dto.NewsCommentsDto;
import ro.ubb.istudent.repository.CommentsRepository;
import ro.ubb.istudent.repository.NewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by catablack.
 */
@Service
public class CommentsService {

    private static final Logger LOG = LoggerFactory.getLogger(NewService.class);
    private CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Optional<List<NewsCommentsDto>> findNewCommentsById(String newId) {
        return commentsRepository.findNewBynewsId(newId)
                .map(this::newCommentsToNewsCommentsDTO);
    }

    private List<NewsCommentsDto> newCommentsToNewsCommentsDTO(List<NewsComments> newsComments) {
        List<NewsCommentsDto> result = new ArrayList<>();
        for (NewsComments com : newsComments) {

            NewsCommentsDto commentsDto = new NewsCommentsDto();
            commentsDto.setId(com.getId().toString());
            commentsDto.setMessage(com.getMessage());
            commentsDto.setNewsId(com.getNewsId());
            commentsDto.setPosterName(com.getPosterName());
            result.add(commentsDto);
        }
        return result;
    }

    public NewsCommentsDto createComment(NewsCommentsDto newsComment) {
        return commentToCommentDTO(commentsRepository.save(commentDTOToEntity(newsComment)));
    }

    private NewsCommentsDto commentToCommentDTO(NewsComments newsComments) {
        NewsCommentsDto commentsDto = new NewsCommentsDto();
        commentsDto.setMessage(newsComments.getMessage());
        commentsDto.setNewsId(newsComments.getNewsId());
        commentsDto.setPosterName(newsComments.getPosterName());
        return commentsDto;
    }

    private NewsComments commentDTOToEntity(NewsCommentsDto newsCommenDto) {
        NewsComments newsComments = new NewsComments();

        newsComments.setMessage(newsCommenDto.getMessage());
        newsComments.setNewsId(newsCommenDto.getNewsId());
        newsComments.setPosterName(newsCommenDto.getPosterName());

        return newsComments;

    }


}
