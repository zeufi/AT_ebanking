#!/usr/bin/bash
TEST_RESULTS_LOCATION="${1:-/home/runner/work/ebanking/ebanking}"
# shellcheck disable=SC2002
TEST_RESULTS_STRING=$(cat "${TEST_RESULTS_LOCATION}/Guru99-zap-report.html")

cat <<EOF | curl --data-binary @- "${PUSHGATEWAY_URL}"/metrics/job/github_actions
github_actions_high_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/High<\/div>/{print $(NF-1)}')
github_actions_medium_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Medium<\/div>/{print $(NF-1)}')
github_actions_low_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Low<\/div>/{print $(NF-1)}')
github_actions_informational_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Informational<\/div>/{print $(NF-1)}')
github_actions_falsePositive_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/False Positives:<\/div>/{print $(NF-1)}')
EOF

# shellcheck disable=SC2129
echo " gha.maventest.high=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/High<\/div>/{print $(NF-1)}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.medium=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Medium<\/div>/{print $(NF-1)}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.low=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Low<\/div>/{print $(NF-1)}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.informational=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Informational<\/div>/{print $(NF-1)}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.falsePositive=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/False Positives:<\/div>/{print $(NF-1)}')" >> "$BUILDEVENT_FILE"

