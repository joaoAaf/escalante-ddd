import InputUpload from '../input_upload'
import Styles from './styles.module.css'

export default function Militares() {
    return (
        <div className={Styles.main}>
            <h2>Militares Escalaveis</h2>
            <div className={Styles.upload}>
                <label htmlFor="input_upload" className={Styles.label_upload}>Importe a Planilha dos Militares</label>
                <InputUpload />
            </div>
        </div>
    )
}