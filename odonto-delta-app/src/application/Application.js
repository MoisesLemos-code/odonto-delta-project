import register from './engine/Register'
import triggerEvents from './engine/TriggerEvent'
import rootPageCreator from './engine/RootPageCreator'
import dotenv from 'dotenv'

export default class Application {

    static run() {
        register.registerAll()
        dotenv.config()

        triggerEvents.triggerOnStartEvents()
            .then(() => {
                rootPageCreator.createInstance()
            })
            .catch(() => {
                rootPageCreator.createBootstrapError()
            })
    }

}
