#!/usr/bin/bash

###################################################################################################
#     This is extracting the values for each test suite description.                              #
#     It uses awk to print the value between two quotes.                                          #
#     The columns printed is based on the following example string:                               #
###################################################################################################

TEST_RESULTS_LOCATION="${1:-/home/runner/work/ebanking/ebanking}"
# shellcheck disable=SC2002
TEST_RESULTS_STRING=$(cat "${TEST_RESULTS_LOCATION}/Guru99-zap-report.html")

cat <<EOF | curl --data-binary @- "${PUSHGATEWAY_URL}"/metrics/job/github_actions
github_actions_high_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/High/{print $3}')
github_actions_medium_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Medium/{print $3}')
github_actions_low_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Low/{print $3}')
github_actions_informational_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Informational/{print $3}')
github_actions_falsePositive_risk_chrome{action_id="${GITHUB_RUN_NUMBER}",commit="${GITHUB_SHA}",actor="${GITHUB_ACTOR}",branch="${GITHUB_REF}",os="${RUNNER_OS}",browser="${GITHUB_JOB}",environment="${ENVIRONMENT}"} $(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/False Positives/{print $3}')
EOF

# shellcheck disable=SC2129
echo " gha.maventest.high=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/High/{print $3}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.medium=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Medium/{print $3}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.low=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Low/{print $3}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.informational=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/Informational/{print $3}')" >> "$BUILDEVENT_FILE"
echo " gha.maventest.falsePositive=$(echo "${TEST_RESULTS_STRING}" | awk -F'[><]' '/False Positives/{print $3}')" >> "$BUILDEVENT_FILE"

