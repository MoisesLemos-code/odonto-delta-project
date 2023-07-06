import moment from 'moment-timezone'
import { state } from '@/core/store/comum'

const filterDate = (date) => {
    if (date) {
        return moment(date)
            .tz(state.comum.timezone)
            .format('DD/MM/YYYY')
    }
    return '----'
}

export default filterDate