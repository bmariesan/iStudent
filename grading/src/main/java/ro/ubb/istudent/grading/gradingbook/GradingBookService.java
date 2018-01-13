package ro.ubb.istudent.grading.gradingbook;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradingBookService {
    @Autowired
    private GradingBookRepository gradingBookRepository;

    public GradingBook save(GradingBook gradingBook) {
        return gradingBookRepository.save(gradingBook);
    }

    public Optional<GradingBook> getByID(ObjectId id) {
        return gradingBookRepository.findById(id);
    }
}
