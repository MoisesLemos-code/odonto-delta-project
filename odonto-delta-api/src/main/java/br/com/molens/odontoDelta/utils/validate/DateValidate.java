package br.com.molens.odontoDelta.utils.validate;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DateValidate {

    public static String formatarData(LocalDateTime date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return formatter.format(localDateTimeToDate(date));
    }

    public static Date localDateTimeToDate(LocalDateTime startOfDay) {
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime somarMinutosEmDataHora(LocalDateTime dataHora, int minuto) {
        LocalDateTime dataHoraAlterado;
        if (Objects.nonNull(dataHora)) {
            dataHoraAlterado = dataHora.plusMinutes(minuto);
        } else {
            dataHoraAlterado = LocalDateTime.now().plusMinutes(minuto);
        }
        return dataHoraAlterado;
    }

    public static LocalDateTime gerarDataRandomica(LocalDateTime dataHora, int minimoEmMinutos, int maximoEmMinutos) {
        Random random = ThreadLocalRandom.current();
        int minuto = random.nextInt(maximoEmMinutos - minimoEmMinutos + 1) + minimoEmMinutos;
        return DateValidate.somarMinutosEmDataHora(dataHora, minuto);
    }

}
