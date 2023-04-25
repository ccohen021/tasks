CREATE TABLE IF NOT EXISTS task (
        uuid uuid PRIMARY KEY DEFAULT gen_random_uuid(),
        description TEXT NOT NULL,
        completed BOOLEAN NOT NULL
);
