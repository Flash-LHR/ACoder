const login = {
    state:{
        loginUsername: '',               //用户账号
        loginPassword: '',               //用户头像
    },
    getters: {
        loginUsername: state => {
            let loginUsername = state.loginUsername
            if(!loginUsername){
                loginUsername = JSON.parse(window.sessionStorage.getItem('loginUsername')) 
            }
            return loginUsername
        },
        loginPassword: state => {
            let loginPassword = state.loginPassword
            if(!loginPassword){
                loginPassword = JSON.parse(window.sessionStorage.getItem('loginPassword')) 
            }
            return loginPassword
        },
    },
    mutations: {
        setLoginUsername: (state,loginUsername) => {
            state.loginUsername = loginUsername
            window.sessionStorage.setItem('loginUsername',JSON.stringify(loginUsername))
        },
        setLoginPassword: (state,loginPassword) => {
            state.loginPassword = loginPassword
            window.sessionStorage.setItem('loginPassword',JSON.stringify(loginPassword))
        },
    }
}

export default login