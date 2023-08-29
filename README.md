# hyphen-backend-user

<br>

### Commit 규칙

#### Commit 기본 형식

```
(gitmoji) :: 구체적인 커밋 메시지
```

#### gitmoji 타입

| icon |     code    |           description            |
|:---:|:--------:|:--------------------------------:|
| ✨   | `:sparkles:` |        새 기능 (파일 추가)            |
| 📝   |  `:memo:`    |     코드 수정 (요구사항 수정)    |
| 🎨   |   `:art:`    |     코드 구조 개선        |
| ⚡️    |    `:zap:`    |   코드 성능 개선        |
| 🔥   |    `:fire:`  |   코드 삭제 (파일 삭제)   |
| 📄 |    `:page_facing_up:`  |   문서 작성 및 변경    |
| 🔧 |  `:wrench:`      | Configuration 파일/의존성 추가 및 삭제 |
| 👷 |   `:construction_worker:`    |   CI/CD 시스템 추가/수정     |
| 🐛 |     `:bug:`    |         버그 수정               |
| ✅ |  `:white_check_mark:`   |      테스트 케이스 작성 및 수정    |
| ⏪ | `:rewind:` |            작업 되돌리기              |
| 🚑 |   `:ambulance:`    |          긴급 수정             |
| 🙈 | `:see_no_evil:`  | .gitignore 추가/수정 |

<br><br>

### Version 규칙

> 모든 버전은 01.00.00에서 시작합니다.
```
"01.01.09" 생략시 "1.1.9"
"01.01.10" 생략시 "1.1.10"
```
- 기존 버전과 호환되지 않도록 API가 변경되면 **Major Version**를 올립니다.
- 기존 버전과 호환되면서 새로운 기능을 추가할 때는 **Minor Version**를 올립니다.
- 자잘한 버그나 내부적 코드 보완 등의 변화가 발생했을때 **Patches**를 올립니다.