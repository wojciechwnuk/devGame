package Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class ConsolePrinterService implements PrinterService {

    private static final Logger logger = LoggerFactory.getLogger(ConsolePrinterService.class);

    @Override
    public <T> void print(Collection<T> values) {
        values.forEach(this::print);
    }

    @Override
    public <T> void print(T value) {
        logger.info(value.toString());
    }
}