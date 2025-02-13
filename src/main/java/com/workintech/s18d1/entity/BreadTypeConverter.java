import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.workintech.s18d1.entity.BreadType;

@Component
public class BreadTypeConverter implements Converter<String, BreadType> {

    @Override
    public BreadType convert(String source) {
        try {
            return BreadType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid bread type: " + source);
        }
    }
}
